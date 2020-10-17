package com.he.springcloud.entities;


//返回给前端的通用json数据串

public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data; //泛型，对应类型的json数据
    //自定义两个参数的构造方法
    public CommonResult(Integer code, String message){
        this(code,message,null);
    }

    public CommonResult() {
    }

    public CommonResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
