package hello.controller;

import hello.entity.*;
import hello.service.UserService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Map;

@RestController
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @Inject
    public AuthController(AuthenticationManager authenticationManager,
                          UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @RequestMapping("/")
    public User index(@RequestParam("id") int id) {
        return this.userService.getUserById(id);
    }

    @GetMapping("/auth")
    public Object auth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userService.getUserByUsername(authentication == null ? null : authentication.getName());

        if (user == null) {
            return LoginResult.success( "用户未登录", false);
        } else {
            return LoginResult.success("登录成功", true, user);
        }
    }

    @PostMapping("/auth/register")
    public LoginResult register(@RequestBody Map<String, String> usernameAndPassword) {
        String username = usernameAndPassword.get("username");
        String password = usernameAndPassword.get("password");
        User user = userService.getUserByUsername(username);
        try {
            userService.save(username, password);
        } catch (DuplicateKeyException e) {
            return LoginResult.failure( "用户已经存在", false);
        }
        return LoginResult.success("注册成功!", false);
    }

    @PostMapping("/auth/login")
    public Result login(@RequestBody Map<String, String> usernameAndPassword) {
        String username = usernameAndPassword.get("username");
        String password = usernameAndPassword.get("password");
        UserDetails userDetails;
        try {
            userDetails = userService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            return LoginResult.failure("用户不存在", false);
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        try {
            authenticationManager.authenticate(token);

            SecurityContextHolder.getContext().setAuthentication(token);

            return LoginResult.success( "登录成功", true);
        } catch (BadCredentialsException e) {
            return LoginResult.failure("用户不存在或密码不正确", false);
        }
    }
}
