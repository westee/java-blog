package hello.entity;

public abstract class Result<T> {
    String msg;
    String status;
    T data;

    protected Result(String status, String msg, boolean isLogin) {
        this(msg, status, null);
    }

    protected Result(String msg, String status, T data) {
        this.msg = msg;
        this.status = status;
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

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

