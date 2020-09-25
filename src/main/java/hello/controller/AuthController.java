package hello.controller;

import hello.entiry.User;
import hello.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Map;

@RestController
public class AuthController {
    private UserService userService;
    private AuthenticationManager authenticationManager;

    @Inject
    public AuthController(AuthenticationManager authenticationManager,
                          UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService =userService;
    }

    @RequestMapping("/")
    public User index(@RequestParam("id") int id){
        return this.userService.getUserById(id);
    }

    @GetMapping("/auth")
    public Object auth(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userService.getUserByUsername(name);

        if(user == null){
            return new Result("ok", "用户未登录", false);
        } else {
            return new Result("ok", "登录成功", true, user);
        }
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

            SecurityContextHolder.getContext().setAuthentication(token);

            return new Result("ok", "登录成功", true, userService.getUserByUsername(username));
        } catch (BadCredentialsException e){
            return new Result("fail","用户不存在或密码不正确", false);
        }
    }

    private static class Result {
        String msg;
        String status;
        boolean isLogin;
        Object data;

        public Result(String status, String msg, boolean isLogin){
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
