package com.io.ssm.module.web;

import com.io.ssm.framework.common.BaseController;
import com.io.ssm.framework.common.Result;
import com.io.ssm.framework.security.shiro.CaptchaException;
import com.io.ssm.framework.security.shiro.CaptchaUsernamePasswordToken;
import com.io.ssm.framework.utils.Constants;
import com.io.ssm.framework.utils.RandomUtils;
import com.io.ssm.framework.utils.UserUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: llyong
 * @date: 2018/8/14
 * @time: 19:27
 * @version: 1.0
 */
@Controller
public class LoginController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);


    @RequestMapping(value = {"index","","/"})
    public String index() {
        return "index";
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String turnLogin () {
        return "login";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    public Result login (String username, String password, boolean rememberMe,String captcha) {
        Subject subject = SecurityUtils.getSubject();
        CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken(username, password,rememberMe,captcha);
        try {
            subject.login(token);
            subject.getSession().setAttribute("userInfo",UserUtil.getUser());
            return Result.ok("成功");
        } catch (LockedAccountException e) {
            LOGGER.error("登录失败，该用户已被冻结！",e);
            return Result.error("登录失败，该用户已被冻结！");
        } catch (AccountException e) {
            LOGGER.error("登录失败",e);
            return Result.error("用户或者密码错误！");
        } catch (CaptchaException e) {
            LOGGER.error("验证码异常",e);
            return Result.error("验证码错误！");
        }   catch (AuthenticationException e) {
            LOGGER.error("该用户不存在！",e);
            return Result.error("该用户不存在！");
        } catch (Exception e) {
            LOGGER.error("系统异常",e);
            return Result.error("系统异常！");
        }
    }

    /**
     * 获取验证码
     * @param request
     * @return
     */
    @RequestMapping(value="/captcha",method = RequestMethod.POST)
    @ResponseBody
    public String getCaptchaCode (HttpServletRequest request){
        try {
            String code = RandomUtils.getRandomCode(4);
            request.getSession().setAttribute(Constants.SESSION_CAPTCHA_CODE,code);
            return code;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
