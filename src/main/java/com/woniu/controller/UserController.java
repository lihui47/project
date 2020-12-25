package com.woniu.controller;


import com.woniu.domin.User;
import com.woniu.dto.Result;
import com.woniu.service.UserService;
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
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Result<Object> register(@RequestBody User user){
        return userService.register(user);
    }

    @PostMapping("/login")
    public  Result<Object> login(@RequestBody User user) {
        return userService.login(user);
    }
}

