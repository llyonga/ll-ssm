package com.io.ssm.module.domain.rolemenu;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CmRoleMenuMapper {
    long countByExample(CmRoleMenuExample example);

    int deleteByExample(CmRoleMenuExample example);

    int insert(CmRoleMenu record);

    int insertSelective(CmRoleMenu record);

    List<CmRoleMenu> selectByExampleWithRowbounds(CmRoleMenuExample example, RowBounds rowBounds);

    List<CmRoleMenu> selectByExample(CmRoleMenuExample example);

    int updateByExampleSelective(@Param("record") CmRoleMenu record, @Param("example") CmRoleMenuExample example);

    int updateByExample(@Param("record") CmRoleMenu record, @Param("example") CmRoleMenuExample example);

}