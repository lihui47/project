package com.woniu.controller;


import com.woniu.domin.User;
import com.woniu.dto.Result;
import com.woniu.service.PermissionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/permission")
public class PermissionController {
    @Resource
    private PermissionService permissionService;

    @GetMapping("/queryPermission")
    public Result<Object> query(User user){
        return permissionService.queryPermissionName(user.getUsername());
    }

}

