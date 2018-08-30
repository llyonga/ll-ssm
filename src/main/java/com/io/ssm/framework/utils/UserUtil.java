package com.io.ssm.framework.utils;

import com.io.ssm.module.domain.user.CmUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @description: 用户信息工具类
 * @author: llyong
 * @date: 2018/8/30
 * @time: 17:15
 * @version: 1.0
 */
public class UserUtil {

    public static CmUser getUser() {
        Subject subject = SecurityUtils.getSubject();
        String principal = (String) subject.getPrincipal();
        CmUser user = new CmUser();
        user.setUsername(principal);
        return user;
    }
}
