package com.io.ssm.module.web.user;

//import com.io.ssm.framework.annotation.ControllerLog;
import com.io.ssm.module.domain.user.CmUser;
import com.io.ssm.framework.common.PageListDto;
import com.io.ssm.module.service.user.CmUserService;
import com.io.ssm.framework.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: llyong
 * @date: 2018/8/9
 * @time: 14:48
 * @version: 1.0
 */
@Controller
@RequestMapping("user")
public class CmUserController extends BaseController {

    @Autowired
    private CmUserService cmUserService;

    @RequestMapping("getOne")
    public @ResponseBody CmUser getOne() {
        CmUser cmUser = cmUserService.selectByPrimaryKey(23L);
        System.out.println(cmUser);
        return cmUser;
    }

    @RequestMapping(value = "userList",method = RequestMethod.GET)
    public String userList() {
        return "user/list";
    }

//    @ControllerLog(description="用户列表")
    @RequestMapping("getList")
    public @ResponseBody PageListDto<CmUser> getList(CmUser cmUser) {
        PageListDto<CmUser> userList = cmUserService.selectByExampleWithRowbounds(cmUser,getRowBounds(),getOrderBy());
        return userList;
    }
}
