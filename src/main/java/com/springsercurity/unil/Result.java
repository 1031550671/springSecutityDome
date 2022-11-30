package com.springsercurity.unil;

public class Result<T> {
    private Integer code;
    private String msg;
    private T data;
//    private boolean isEncrypt;

    public Result(T data) {
        this.data = data;
    }

    public Result() {
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
