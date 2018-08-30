package com.io.ssm.framework.security.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @description:
 * @author: llyong
 * @date: 2018/8/30
 * @time: 12:26
 * @version: 1.0
 */
public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {

    private String captcha;

    public CaptchaUsernamePasswordToken(String username, String password, boolean rememberMe, String captcha) {
        super(username,password,rememberMe);
        this.captcha = captcha;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
