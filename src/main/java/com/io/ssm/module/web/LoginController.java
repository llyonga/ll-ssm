package com.io.ssm.module.web;

import com.io.ssm.framework.common.BaseController;
import com.io.ssm.framework.common.Result;
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
    public String turnLogin (Model model) {
        return "login";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    public Result login (String username, String password, boolean rememberMe) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            return Result.ok("成功");
        } catch (IncorrectCredentialsException e) {
            LOGGER.error("用户或者密码错误！",e);
            return Result.error("用户或者密码错误！");
        } catch (LockedAccountException e) {
            LOGGER.error("登录失败，该用户已被冻结！",e);
            return Result.error("登录失败，该用户已被冻结！");
        } catch (AccountException e) {
            LOGGER.error("",e);
            return Result.error("用户或者密码错误！");
        } catch (AuthenticationException e) {
            LOGGER.error("该用户不存在！",e);
            return Result.error("该用户不存在！");
        } catch (Exception e) {
            LOGGER.error("系统异常",e);
            return Result.error("系统异常！");
        }
    }
}
