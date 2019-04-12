package jock.demo.controller;

public class MyReponseBody {
    //the code meaning
    public static final int SUCCESS = 1;
    public static final int FAILED = 0;

    private int code;
    private String message;
    private Object data;

    public MyReponseBody(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static MyReponseBody ok() {
        return new MyReponseBody(SUCCESS, "SUCCESS", null);
    }

    public static MyReponseBody ok(Object data) {
        return new MyReponseBody(SUCCESS, "SUCCESS", data);
    }

    public static MyReponseBody failed(String message) {
        return new MyReponseBody(FAILED, message, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(byte code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
