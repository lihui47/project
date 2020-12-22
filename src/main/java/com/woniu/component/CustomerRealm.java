package com.woniu.component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.domin.Role;
import com.woniu.domin.User;
import com.woniu.mapper.UserMapper;
import com.woniu.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Alex
 */
public class CustomerRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;
    @Resource
    private UserMapper userMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("开始授权------------------------------------");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principalCollection.getPrimaryPrincipal();
        List<Role> roles = userMapper.findRolesById(user.getId());
        roles.forEach(role -> simpleAuthorizationInfo.addRole(role.getName()));
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String principal = (String) token.getPrincipal();
        //整合mybatis后再查询数据库获取
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",principal);
        User user = userService.getOne(queryWrapper);
        //根据用户名查询得到的用户不为空
        if (!ObjectUtils.isEmpty(user)) {
            return new SimpleAuthenticationInfo(user,user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()),
                this.getName());
        }
        return null;
    }
}
