package com.io.ssm.framework.security.shiro;

import org.apache.shiro.authc.AuthenticationException;

/**
 * @description: 图片验证异常
 * @author: llyong
 * @date: 2018/8/30
 * @time: 14:15
 * @version: 1.0
 */
public class CaptchaException extends AuthenticationException {

    public CaptchaException() {
    }

    public CaptchaException(String message) {
        super(message);
    }

    public CaptchaException(Throwable cause) {
        super(cause);
    }

    public CaptchaException(String message, Throwable cause) {
        super(message, cause);
    }
}
