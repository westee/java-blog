package hello.controller;

import hello.entiry.User;
import hello.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Map;

@RestController
public class AuthController {
    private UserDetailsService userDetailsService;
    private AuthenticationManager authenticationManager;
    private UserService userService;

    @Inject
    public AuthController(UserDetailsService userDetailsService,
                          AuthenticationManager authenticationManager,
                          UserService userService) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.userService =userService;
    }

    @RequestMapping("/")
    public User index(@RequestParam("id") int id){
        return this.userService.getUserById(id);
    }

    @GetMapping("/auth")
    public Object auth(){
        return new Result("ok", "登录成功", true);
    }

    @PostMapping("/auth/login")
    public Result login(@RequestBody Map<String, String> usernameAndPassword){
        String username = usernameAndPassword.get("username");
        String password = usernameAndPassword.get("password");
        UserDetails userDetails;
        try{
            userDetails = userService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e){
            return new Result("fail","用户不存在", false);
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        try{
            authenticationManager.authenticate(token);
            User user = new User(1, userDetails.getUsername());
            return new Result("ok", "登录成功", true, user);
        } catch (BadCredentialsException e){
            return new Result("fail","用户不存在或密码不正确", false);
        }
    }

    private static class Result {
        String msg;
        String status;
        boolean isLogin;
        Object data;

        public Result(String msg, String status, boolean isLogin){
            this(msg, status, isLogin, null);
        }

        public Result(String msg, String status, boolean isLogin, Object data) {
            this.msg = msg;
            this.status = status;
            this.isLogin = isLogin;
            this.data = data;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public boolean isLogin() {
            return isLogin;
        }

        public void setLogin(boolean login) {
            isLogin = login;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }
}
