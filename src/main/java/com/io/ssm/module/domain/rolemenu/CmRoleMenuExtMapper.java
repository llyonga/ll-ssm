package com.io.ssm.module.domain.rolemenu;

import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description:
 * @author: llyong
 * @date: 2018/9/14
 * @time: 10:52
 * @version: 1.0
 */
public interface CmRoleMenuExtMapper {
    @Select("SELECT m.* FROM cm_role_menu rm,cm_user u,cm_user_role ur,cm_menu m WHERE u.USERNAME = #{username} AND u.USER_ID=ur.USER_ID AND ur.ROLE_ID=rm.ROLE_ID AND rm.MENU_ID=m.MENU_ID")
    List<CmRoleMenu> findAllMenu(String username);
}
