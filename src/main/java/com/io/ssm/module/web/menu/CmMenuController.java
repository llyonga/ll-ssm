package com.io.ssm.module.web.menu;

import com.io.ssm.framework.annotations.ControllerLog;
import com.io.ssm.framework.common.Result;
import com.io.ssm.framework.enumtype.SeqEnum;
import com.io.ssm.framework.utils.JSONUtil;
import com.io.ssm.framework.utils.StringUtils;
import com.io.ssm.framework.utils.UserUtil;
import com.io.ssm.framework.utils.seq.SeqUtil;
import com.io.ssm.module.domain.menu.CmMenu;
import com.io.ssm.module.service.menu.CmMenuService;
import com.io.ssm.framework.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.registry.infomodel.User;
import java.util.Date;
import java.util.List;

/**
 * @description: 菜单管理请求处理类
 * @author: llyong
 * @date: 2018/8/11
 * @time: 18:48
 * @version: 1.0
 */
@Controller
@RequestMapping("menu")
public class CmMenuController extends BaseController {

    @Autowired
    private CmMenuService cmMenuService;

    @ControllerLog(description="跳转到菜单管理页面")
    @RequestMapping(value = "menuList",method = RequestMethod.GET)
    public String menuList() {
        try {
            return "menu/list";
        } catch (Exception e) {
            e.printStackTrace();
            return "error/404";
        }
    }

    @RequestMapping("getList")
    @ResponseBody
    public List<CmMenu> getList(CmMenu cmMenu) {
        return cmMenuService.selectByExample(cmMenu);
    }

    @ControllerLog(description="获取Tree的数据")
    @RequestMapping("getMenuTree")
    @ResponseBody
    public List<CmMenu> getMenuTree(CmMenu cmMenu) {
        return cmMenuService.getMenuTree(cmMenu);
    }

    @ControllerLog(description="获取单个菜单的数据")
    @RequestMapping("getMenuOne")
    @ResponseBody
    public Result getMenuOne(CmMenu cmMenu) {
        try {
            CmMenu menu = cmMenuService.selectByExample(cmMenu).get(0);
            return new Result(true,menu);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"获取数据失败");
        }
    }

    @ControllerLog(description = "保存菜单信息")
    @RequestMapping("addMenu")
    @ResponseBody
    public Result addMenu(String json) {
        try {
            CmMenu cmMenu = JSONUtil.toBean(CmMenu.class, json);
            if (StringUtils.isEmpty(cmMenu.getMenuId())) {
                cmMenu.setMenuId(SeqUtil.getSeq(SeqEnum.MENU));
                cmMenu.setCreater(UserUtil.getUser().getUsername());
                cmMenu.setCreateTime(new Date());
                cmMenuService.insertSelective(cmMenu);
            }else {
                cmMenu.setModifier(UserUtil.getUser().getUsername());
                cmMenu.setModifyTime(new Date());
                cmMenuService.updateByPrimaryKeySelective(cmMenu);
            }
            return new Result(true,"保存成功",cmMenu);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("保存失败");
        }
    }

//    public @ResponseBody String getList() {
//        String s="[{\"id\":1,\"pid\":0,\"status\":1,\"name\":\"系统管理\",\"permissionValue\":\"open:system:get\"},{\"id\":2,\"pid\":0,\"status\":1,\"name\":\"字典管理\",\"permissionValue\":\"open:dict:get\"},{\"id\":20,\"pid\":1,\"status\":1,\"name\":\"新增系统\",\"permissionValue\":\"open:system:add\"},{\"id\":21,\"pid\":1,\"status\":1,\"name\":\"编辑系统\",\"permissionValue\":\"open:system:edit\"},{\"id\":22,\"pid\":1,\"status\":1,\"name\":\"删除系统\",\"permissionValue\":\"open:system:delete\"},{\"id\":33,\"pid\":2,\"status\":1,\"name\":\"系统环境\",\"permissionValue\":\"open:env:get\"},{\"id\":333,\"pid\":33,\"status\":1,\"name\":\"新增环境\",\"permissionValue\":\"open:env:add\"},{\"id\":3333,\"pid\":33,\"status\":1,\"name\":\"编辑环境\",\"permissionValue\":\"open:env:edit\"},{\"id\":233332,\"pid\":33,\"status\":0,\"name\":\"删除环境\",\"permissionValue\":\"open:env:delete\"}]";
//        return s;
//    }




}
