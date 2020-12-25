package com.woniu.service.impl;

import com.woniu.domin.Permission;
import com.woniu.domin.Role;
import com.woniu.dto.Result;
import com.woniu.dto.StatusCode;
import com.woniu.mapper.PermissionMapper;
import com.woniu.mapper.RoleMapper;
import com.woniu.mapper.UserMapper;
import com.woniu.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author team
 * @since 2020-12-21
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Override
    public Result<Object> queryPermissionName(String username) {
        List<Role> roles = userMapper.findRoles(username);
        List<String> roleNames = new ArrayList<>();
        List<String> permsNames = new ArrayList<>();

        if (!ObjectUtils.isEmpty(roles)) {
            System.out.println(ObjectUtils.isEmpty(roles));
            for (Role role : roles) {
                System.out.println(role.getName());
                roleNames.add(role.getName());
                //根据角色查询权限
                List<Permission> permissions = roleMapper.getPermissions(role.getId());
                if (!CollectionUtils.isEmpty(permissions)) {
                    for (Permission permission : permissions) {
                        permsNames.add(permission.getPermissionName());
                    }
                }else {
                    return new Result<>(false,StatusCode.ERROR,"该用户未审核");
                }
            }
        }else{
            return new Result<>(false,StatusCode.ERROR,"该用户未审核");
        }
        return new Result<>(true, StatusCode.OK,"查询权限名称成功",permsNames);

    }
}
