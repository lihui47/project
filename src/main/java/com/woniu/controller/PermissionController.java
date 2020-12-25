package com.woniu.controller;


import com.woniu.domin.User;
import com.woniu.dto.Result;
import com.woniu.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = {"授权接口controller"})
public class PermissionController {
    @Resource
    private PermissionService permissionService;

    @GetMapping("/queryPermission")
    @ApiOperation(value = "查询权限")
    public Result<Object> query(User user){
        return permissionService.queryPermissionName(user.getUsername());
    }

}

