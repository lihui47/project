package com.woniu.controller;


import com.woniu.domin.User;
import com.woniu.dto.Result;
import com.woniu.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author team
 * @since 2020-12-21
 */
@RestController
@RequestMapping("/user")
@Api(tags = {"用户入口界面接口controller"})
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    public Result<Object> register(@RequestBody User user){
        return userService.register(user);
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public  Result<Object> login(@RequestBody User user) {
        return userService.login(user);
    }
}

