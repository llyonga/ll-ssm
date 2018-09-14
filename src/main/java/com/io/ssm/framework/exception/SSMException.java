package com.io.ssm.framework.exception;

/**
 * @description:  自定义异常类
 * @author: llyong
 * @date: 2018/9/1
 * @time: 17:23
 * @version: 1.0
 */
public class SSMException extends RuntimeException {

    public SSMException() {
        super();
    }

    public SSMException(String message) {
        super(message);
    }

    public SSMException(String message, Throwable cause) {
        super(message, cause);
    }

    public SSMException(Throwable cause) {
        super(cause);
    }

    protected SSMException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
