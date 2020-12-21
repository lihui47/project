package com.woniu.controller;


import com.woniu.domin.User;
import com.woniu.dto.Result;
import com.woniu.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @PostMapping("register")
    public Result<Object> register(@RequestBody User user){
        return userService.register(user);
    }

    @GetMapping("login")
    public  Result<Object> login(User user) {
        return userService.login(user);
    }
}

