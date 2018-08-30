package com.io.ssm.framework.security.shiro;

import com.io.ssm.module.web.LoginController;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description:
 * @author: llyong
 * @date: 2018/8/30
 * @time: 16:26
 * @version: 1.0
 */
public class ShiroSessionListener implements SessionListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroSessionListener.class);

    /**
     * 会话创建触发 已进入shiro的过滤连就触发这个方法
     * @param session
     */
    @Override
    public void onStart(Session session) {
        // TODO Auto-generated method stub
        LOGGER.info("会话创建：" + session.getId());
    }

    /**
     * 退出
     * @param session
     */
    @Override
    public void onStop(Session session) {
        // TODO Auto-generated method stub
        LOGGER.info("退出会话：" + session.getId());
    }

    /**
     * 会话过期时触发
     * @param session
     */
    @Override
    public void onExpiration(Session session) {
        // TODO Auto-generated method stub
        LOGGER.info("会话过期：" + session.getId());
    }

}