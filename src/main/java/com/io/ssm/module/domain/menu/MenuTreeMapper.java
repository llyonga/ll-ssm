package com.io.ssm.module.domain.menu;

import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description:
 * @author: llyong
 * @date: 2018/8/30
 * @time: 22:16
 * @version: 1.0
 */
public interface MenuTreeMapper {

    @Select("SELECT MENU_ID menuId, PARENT_ID parentId, MENU_NAME menuName,LEVEL level FROM cm_menu")
    List<CmMenu> selectByExample(CmMenuExample example);
}
