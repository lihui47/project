package com.woniu.component;

import com.woniu.domin.Permission;
import com.woniu.domin.Role;
import com.woniu.mapper.RoleMapper;
import com.woniu.mapper.UserMapper;
import com.woniu.util.JwtUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex
 */
@Component
public class CustomerRealm extends AuthorizingRealm {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("————权限授予方法————");
        //开始授权
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String username = JwtUtil.getUsername(principals.toString());
        //从令牌中获取用户名，查询该用户拥有的角色
        List<Role> roles = userMapper.findRoles(username);
        List<String> roleNames = new ArrayList<>();
        List<String> permsNames = new ArrayList<>();

        if (!CollectionUtils.isEmpty(roles)) {
            for (Role role : roles) {
                roleNames.add(role.getName());
                //根据角色查询权限
                List<Permission> permissions = roleMapper.getPermissions(role.getId());
                if (!CollectionUtils.isEmpty(permissions)) {
                    for (Permission permission : permissions) {
                        permsNames.add(permission.getPermissionName());
                    }
                }
            }
            //为主体指定角色
            simpleAuthorizationInfo.addRoles(roleNames);
            //为主体指定可见资源
            simpleAuthorizationInfo.addStringPermissions(permsNames);
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JwtUtil.getUsername(token);
        if (username == null || !JwtUtil.verify(token, username)) {
            throw new AuthenticationException("token认证失败！");
        }
        return new SimpleAuthenticationInfo(token, token, this.getName());
    }
}
