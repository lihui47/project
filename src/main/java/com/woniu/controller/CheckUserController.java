package com.woniu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.domin.User;
import com.woniu.dto.Result;
import com.woniu.dto.StatusCode;
import com.woniu.service.UserRolerService;
import com.woniu.service.UserService;
import com.woniu.vo.UserBlurVo;
import com.woniu.vo.UserPageVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/*
审核用户的接口
 */

@RestController
//@CrossOrigin
@RequestMapping("/check")
public class CheckUserController {
    @Resource
    private UserService userService;
    @Resource
    private UserRolerService userRolerService;
    /*
    查询所有需要审核的用户
     */
    @GetMapping("showCheck")
    public Result ShowCheckUser(UserPageVo userPageVo){
        System.out.println(userPageVo);
       Page list= userService.findAllCheckUser(userPageVo);
        return new Result(true, StatusCode.OK,"查询成功" ,list);
    }
    /*
    点击审核，审核用户
     */
    @GetMapping("checkUser")
    public Result checkUser(User user){
        System.out.println(user);
        int row=userService.updateUserById(user);
        //授予用户角色
        userRolerService.InsertUserRole(user);
        return new Result(true,StatusCode.OK,"审核通过");
    }
    /*
    点击驳回，驳回用户
     */
    @GetMapping("reject")
    public Result rejectUser(User user){
        int i = userService.updateRejectUserById(user);
        return new Result(true,StatusCode.OK,"驳回成功");
    }
    /*
    展示所有用户,分页
     */
    @GetMapping("AllUser")
    public Result selectAllUser(UserPageVo userPageVo){
        System.out.println("真的好烦呀");
       Page page= userService.findAllUser(userPageVo);
        return new Result(true,StatusCode.OK,"查询成功",page);
    }
    /*
    删除用户
     */
    @GetMapping("deleteUser")
    public Result UpdateUserById(User user){
        System.out.println(12345);
        System.out.println(user);
        int row=userService.deleteUserById(user.getId());
        return new Result(true,StatusCode.OK,"删除成功");
    }
    /*
    用户管理的模糊查询
     */
    @GetMapping("blurSelect")
    public Result SelectUserByBlur(UserBlurVo blur) {
        System.out.println(blur);
        List<User> list=userService.findUserByBlur(blur);
        System.out.println(list);
    return new Result(true,StatusCode.OK,"模糊查询成功",list);
    }
    /*
    禁用用户
     */
    @GetMapping("prevent")
    public Result PreventUser(User user){
        int i = userService.updatePreventUserById(user);
       return new Result(true,StatusCode.OK,"禁用成功") ;
    }
}
