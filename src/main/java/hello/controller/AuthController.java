package hello.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Map;

@RestController
public class AuthController {
    UserDetailsService userDetailsService;
    AuthenticationManager authenticationManager;
    @Inject
    public AuthController(UserDetailsService userDetailsService, AuthenticationManager authenticationManager) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/auth")
    public Object auth(){
        return new Result("ok", "登录成功", true);
    }

    @PostMapping("/auth/login")
    public Result login(@RequestBody Map<String, String> usernameAndPassword){
        String username = usernameAndPassword.get("username");
        String password = usernameAndPassword.get("password");
//        UserDetails userDetails;
//        try{
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        } catch (UsernameNotFoundException e){
//            return new Result("fail","用户不存在", false);
//        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(token);
        return null;
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
