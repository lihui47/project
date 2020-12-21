package com.woniu.controller;

import com.woniu.domin.User;
import com.woniu.dto.Result;
import com.woniu.dto.StatusCode;
import com.woniu.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/*
审核用户的接口
 */
@RestController("/check")
public class CheckUserController {
    @Resource
    private UserService userService;
    /*
    查询所有需要审核的用户
     */
    @GetMapping("showCheck")
    public Result ShowCheckUser(){
       List<User> list= userService.findAllCheckUser();
        return new Result(true, StatusCode.OK,"审核成功" ,list);
    }
    /*
    点击审核，审核用户
     */
    @GetMapping("checkUser")
    public Result checkUser(User user){
        int row=userService.updateUserById(user);
        return new Result(true,StatusCode.OK,"审核通过");
    }
}
