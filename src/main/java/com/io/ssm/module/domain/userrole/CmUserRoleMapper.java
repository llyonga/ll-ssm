package com.io.ssm.module.domain.userrole;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CmUserRoleMapper {
    long countByExample(CmUserRoleExample example);

    int deleteByExample(CmUserRoleExample example);

    int insert(CmUserRole record);

    int insertSelective(CmUserRole record);

    List<CmUserRole> selectByExampleWithRowbounds(CmUserRoleExample example, RowBounds rowBounds);

    List<CmUserRole> selectByExample(CmUserRoleExample example);

    int updateByExampleSelective(@Param("record") CmUserRole record, @Param("example") CmUserRoleExample example);

    int updateByExample(@Param("record") CmUserRole record, @Param("example") CmUserRoleExample example);
}