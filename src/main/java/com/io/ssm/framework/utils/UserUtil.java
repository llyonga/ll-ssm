package com.io.ssm.framework.utils;

import com.io.ssm.module.domain.user.CmUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description: 用户信息工具类
 * @author: llyong
 * @date: 2018/8/30
 * @time: 17:15
 * @version: 1.0
 */
public class UserUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserUtil.class);

    public static CmUser getUser() {
        CmUser user = new CmUser();
        try {
            Subject subject = SecurityUtils.getSubject();
            String principal = (String) subject.getPrincipal();
            user.setUsername(principal);
        } catch (Exception e) {
            user.setUsername("system");
            LOGGER.error("get current user fail {}");
        }
        return user;
    }
}
