package com.io.ssm.module.service.menu;

import com.io.ssm.framework.common.BaseService;
import com.io.ssm.module.domain.menu.CmMenu;

import java.util.List;

/**
 * @description:
 * @author: llyong
 * @date: 2018/8/18
 * @time: 16:53
 * @version: 1.0
 */
public interface CmMenuService extends BaseService<CmMenu> {
    List<CmMenu> getMenuTree(CmMenu cmMenu);
}
