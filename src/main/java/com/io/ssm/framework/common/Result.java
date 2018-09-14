package com.io.ssm.framework.common;

/**
 * @description: 公共返回类
 * @author: llyong
 * @date: 2018/8/18
 * @time: 18:00
 * @version: 1.0
 */
public class Result<T> {

    private Integer code;
    private String msg;
    private T data;

    public static Result success(String msg) {
        return new Result(true,msg);
    }

    public static Result fail(String msg) {
        return new Result(false,msg);
    }

    public Result(boolean rs, String msg, T data) {
        if (rs) {
            this.code = 1;
        } else {
            this.code = 0;
        }
        this.msg = msg;
        this.data = data;
    }

    public Result(boolean rs, String msg) {
        if (rs) {
            this.code = 1;
        } else {
            this.code = 0;
        }
        this.msg = msg;
        this.data = null;
    }

    public Result(boolean rs, T data) {
        if (rs) {
            this.code = 1;
        } else {
            this.code = 0;
        }
        this.msg = null;
        this.data = data;
    }


    public Result(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
