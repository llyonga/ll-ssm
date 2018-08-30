package com.io.ssm.framework.security.shiro;

import com.io.ssm.framework.utils.Constants;
import com.io.ssm.module.domain.user.CmUser;
import com.io.ssm.module.service.user.CmUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:
 * @author: llyong
 * @date: 2018/8/18
 * @time: 09:50
 * @version: 1.0
 */
public class MyShiroRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    private CmUserService cmUserService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        LOGGER.info("--------------授权验证方法-------------------");
//        String username = (String) SecurityUtils.getSubject().getPrincipal();
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        //获得该用户角色
//        List<CmRoleMenu> allMenu = cmRoleMenuService.findAllMenu(username);
//        Set<String> set = new HashSet<>();
//        //需要将 role 封装到 Set 作为 info.setRoles() 的参数
//        set.add(role);
//        //设置该用户拥有的角色
//        info.setRoles(set);
//        return info;
        return null;
    }

    /**
     * 认证 鉴权
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        LOGGER.info("--------------身份验证方法-------------------");
        CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken) authenticationToken;
        Session session = SecurityUtils.getSubject().getSession();
        String code = (String) session.getAttribute(Constants.SESSION_CAPTCHA_CODE);
        String captcha = token.getCaptcha();
        if (captcha == null) {
            throw new CaptchaException("验证码不能为空！");
        }
        if (!captcha.toUpperCase().equals(code.toUpperCase())) {
            throw new CaptchaException("验证码错误！");
        }
        String username = (String) token.getPrincipal();
        CmUser cmUser = cmUserService.selectByUserName(username);
        if (null == cmUser) {
            throw new AccountException("用户不存在！");
        }
        return new SimpleAuthenticationInfo(username, cmUser.getPassword(), ByteSource.Util.bytes(username), getName());
    }

}
