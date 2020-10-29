package hello.entity;

public class LoginResult extends Result {
    boolean isLogin;
    User user;

    protected LoginResult(String msg, String status, Object data) {
        super(msg, status, data);
    }

    protected LoginResult(String msg, String status, Object data, User user) {
        super(msg, status, data);
        this.user = user;
    }

    public static LoginResult success(String msg, boolean b) {
        return  new LoginResult(msg, "ok",b);
    }

    public static LoginResult success(String msg, boolean b, User user) {
        return  new LoginResult(msg, "ok", b,user);
    }

    public static LoginResult failure(String msg, boolean isLogin) {
        return  new LoginResult(msg, "fail",  isLogin);
    }

    public boolean isLogin() {
        return isLogin;
    }
}
