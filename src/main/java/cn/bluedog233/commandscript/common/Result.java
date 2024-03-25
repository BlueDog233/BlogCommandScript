package cn.bluedog233.commandscript.common;


public class Result<T> {
    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public static <T> Result<T> success(String str) {
        return new Result<T>(1, str, null);
    }
    public static <T> Result<T> success() {
        return new Result<T>(1, "", null);
    }
    public static <T> Result<T> success(String message, T data) {
        return new Result<T>(1, message, data);
    }
    public static <T> Result<T> error(String message) {
        return new Result<T>(0, message, null);
    }

}
