package hello.service;

import hello.entiry.User;
import hello.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    UserMapper userMapper;

    @InjectMocks
    UserService userService;

    @Test
    void save() {
        when(bCryptPasswordEncoder.encode("123")).thenReturn("456");
        userService.save("123","123");
        Mockito.verify(userMapper).save("123", "456");
    }

    @Test
    void getUserByUsername() {
        userService.getUserByUsername("123");
        Mockito.verify(userMapper).findUserByUsername("123");
    }

    @Test
    void loadUserByUsername() {

    }

    @Test
    void throwExceptionWhenUserNotFound(){
        when(userMapper.findUserByUsername("123")).thenReturn(null);
        Assertions.assertThrows(UsernameNotFoundException.class,
                ()-> userService.loadUserByUsername("123"));

    }

    @Test
    void returnUserDetailWhenUserFound(){
        when(userMapper.findUserByUsername("myUser"))
                .thenReturn(new User(123, "123", "456"));
        UserDetails userDetails = userService.loadUserByUsername("myUser");

        Assertions.assertEquals("myUser", userDetails.getUsername());
        Assertions.assertEquals("456", userDetails.getPassword());
    }
}