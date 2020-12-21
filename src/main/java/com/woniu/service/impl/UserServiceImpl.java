package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.config.SaltUtil;
import com.woniu.domin.User;
import com.woniu.dto.Result;
import com.woniu.dto.StatusCode;
import com.woniu.mapper.UserMapper;
import com.woniu.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author team
 * @since 2020-12-21
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * Register result.
     *
     * @param user the user
     * @return the result
     */
    @Override
    public Result<Object> register(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",user.getUsername());
        User user1 = userMapper.selectOne(queryWrapper);
        if(ObjectUtils.isEmpty(user1)){
            String salt = SaltUtil.getSalt(8);
            System.out.println(salt);
            user.setSalt(salt);
            Md5Hash md5Hash = new Md5Hash(user.getPassword(),salt,1024);
            user.setPassword(md5Hash.toHex());
            userMapper.insert(user);
            return new Result<>(true, StatusCode.OK,"注册成功");
        }else {
            return new Result<>(false,StatusCode.USEREXIST,"用户已存在");
        }
    }

    /**
     * Login result.
     *
     * @param user the user
     * @return the result
     */
    @Override
    public Result<Object> login(User user) {
        //在shiroFilter中配置了securityManager，会自动注入到securityUtils中
        Subject subject = SecurityUtils.getSubject();
        //创建令牌
        UsernamePasswordToken authenticationToken = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        //进行认证
        try {
            if(!subject.isAuthenticated()&&!subject.isRemembered()){
                subject.login(authenticationToken);
            }
            //认证通过
            return new Result<>(true,StatusCode.OK,"登录成功");
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            return new Result<>(false,StatusCode.USEREXIST,"用户名不存在");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            return new Result<>(false,StatusCode.LOGINERROR,"密码错误");
        }
    }
}
