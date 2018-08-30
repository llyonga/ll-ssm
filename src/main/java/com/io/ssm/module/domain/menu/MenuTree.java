package com.io.ssm.module.domain.menu;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: llyong
 * @date: 2018/8/30
 * @time: 22:14
 * @version: 1.0
 */
public class MenuTree implements Serializable {
    private Long menuId;

    private Long parentId;

    private String menuName;

    private static final long serialVersionUID = 1L;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
