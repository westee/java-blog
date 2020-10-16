package hello.entity;

public class Result {
    String msg;
    String status;
    boolean isLogin;
    Object data;

    public static Result failure(String message) {
        return new Result("fail", message, false);
    }

    public static Result success(String message) {
        return new Result("ok", message, true);
    }

    public Result(String status, String msg, boolean isLogin) {
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

