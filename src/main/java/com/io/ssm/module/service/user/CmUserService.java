package com.io.ssm.module.service.user;

import com.io.ssm.framework.common.BaseService;
import com.io.ssm.module.domain.user.CmUser;

/**
 * @description:
 * @author: llyong
 * @date: 2018/8/18
 * @time: 16:56
 * @version: 1.0
 */
public interface CmUserService extends BaseService<CmUser> {
    /**
     * 通过username查询用户信息
     * @param username
     * @return
     */
    CmUser selectByUserName(String username);
}
