package com.io.ssm.module.service.menu;

import com.io.ssm.framework.annotations.DataSource;
import com.io.ssm.framework.datasource.DataSourceHolder;
import com.io.ssm.framework.enumtype.DataSourceEnum;
import com.io.ssm.framework.utils.StringUtils;
import com.io.ssm.module.domain.menu.CmMenu;
import com.io.ssm.module.domain.menu.CmMenuExample;
import com.io.ssm.module.domain.menu.CmMenuMapper;
import com.io.ssm.framework.common.BaseService;
import com.io.ssm.framework.common.PageListDto;
import com.io.ssm.module.domain.menu.MenuTreeMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: llyong
 * @date: 2018/8/11
 * @time: 18:45
 * @version: 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CmMenuServiceImpl implements CmMenuService {

    @Autowired
    private CmMenuMapper cmMenuMapper;
    @Autowired
    private MenuTreeMapper menuTreeMapper;

    @Override
    public long countByExample(CmMenu cmMenu) {
        return 0;
    }

    @Override
    public int deleteByExample(CmMenu cmMenu) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Long userId) {
        return 0;
    }

    @Override
    public int insert(CmMenu cmMenu) {
        return 0;
    }

    @Override
    public int insertSelective(CmMenu cmMenu) {
        return 0;
    }

    private CmMenuExample getExampleDto(CmMenu cmMenu) {
        CmMenuExample example = new CmMenuExample();
        CmMenuExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(cmMenu.getMenuId())) {
            criteria.andMenuIdEqualTo(cmMenu.getMenuId());
        }
        return example;
    }

    @Override
    public PageListDto<CmMenu> selectByExampleWithRowbounds(CmMenu cmMenu, RowBounds rowBounds, String orderBy) {
        CmMenuExample example = getExampleDto(cmMenu);
        if (!StringUtils.isEmpty(orderBy)) {
            example.setOrderByClause(orderBy);
        }
        long count = cmMenuMapper.countByExample(example);
        List<CmMenu> list = new ArrayList<CmMenu>();
        if (count > 0) {
            list = cmMenuMapper.selectByExampleWithRowbounds(example, rowBounds);
        }
        PageListDto<CmMenu> pageListDto = new PageListDto<>();
        pageListDto.setRows(list);
        pageListDto.setTotal(count);
        return pageListDto;
    }

    @Override
    public PageListDto<CmMenu> selectByExampleWithRowbounds(CmMenu cmMenu, RowBounds rowBounds) {
        return null;
    }

    @Override
    public List<CmMenu> selectByExample(CmMenu cmMenu) {
        CmMenuExample example = getExampleDto(cmMenu);
        return cmMenuMapper.selectByExample(example);
    }

    @DataSource(DataSource.SLAVE)
    @Override
    public List<CmMenu> getMenuTree(CmMenu cmMenu) {
        CmMenuExample example = getExampleDto(cmMenu);
        return menuTreeMapper.selectByExample(example);
    }

    @Override
    public CmMenu selectByPrimaryKey(Long var) {
        return null;
    }

    @Override
    public int updateByExampleSelective(CmMenu cmMenu) {
        return 0;
    }

    @Override
    public int updateByExample(CmMenu cmMenu) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(CmMenu cmMenu) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(CmMenu cmMenu) {
        return 0;
    }
}
