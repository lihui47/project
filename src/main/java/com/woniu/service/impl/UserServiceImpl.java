package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.domin.User;
import com.woniu.dto.Result;
import com.woniu.dto.StatusCode;
import com.woniu.mapper.UserMapper;
import com.woniu.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.util.JwtUtil;
import com.woniu.util.RedisUtil;
import com.woniu.util.SaltUtil;
import com.woniu.vo.UserBlurVo;
import com.woniu.vo.UserPageVo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import javax.annotation.Resource;
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
@Transactional(rollbackFor = {Exception.class})
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * Register result.
     * 员工注册
     * @param user the user
     * @return the result
     */
    @Override
    public Result<Object> register(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername());
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
     * 员工登录
     * @param user the user
     * @return the result
     */
    @Override
    public Result<Object> login(User user) {
        if(!ObjectUtils.isEmpty(user)){
            User userRedis = userMapper.selectOne(new QueryWrapper<User>().eq("username",user.getUsername()));
            if(ObjectUtils.isEmpty(userRedis)){
                 return new Result<>(false,StatusCode.ACCESSERROR,"当前用户尚未注册！");
            }
            String salt = userRedis.getSalt();
            String hashPassword = new Md5Hash(user.getPassword(), salt, 1024).toHex();
            if (hashPassword.equals(userRedis.getPassword())) {
                String token = JwtUtil.createToken(user.getUsername());
                return new Result<>(true, StatusCode.OK, "登录成功！", token);
            }
            return new Result<>(false,StatusCode.LOGINERROR,"账号密码不匹配！");

        }
        return new Result<>(false,StatusCode.ERROR,"输入有误");
    }
   /**
    *查询所以需要审核的员工
    */
    @Override
    public List<User> findAllCheckUser() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status","已申请");
        return userMapper.selectList(queryWrapper);
    }
   /**
    * 审核通过
    */
    @Override
    public int updateUserById(User user) {
        user.setStatus("已通过");
        int i = userMapper.updateById(user);
        if(i>0){
            return 1;
        }
        return 0;
    }
    /**
     *驳回方法
     */
    @Override
    public int updateRejectUserById(User user) {
        user.setStatus("已驳回");
        int i = userMapper.updateById(user);
        if(i>0){
            return 1;
        }
        return 0;

    }
    /**
     *查询所有用户，用于展示
     */
    @Override
    public Page findAllUser(UserPageVo userPageVo) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("status","已申请");
        Page<User> page = new Page<>(userPageVo.getCurrent(), userPageVo.getSize());
        Page<User> userPage = userMapper.selectPage(page, queryWrapper);
        return userPage;
    }
    /*
    删除用户
     */
    @Override
    public int deleteUserById(Integer id) {
        int row = userMapper.deleteById(id);
        if(row>0){
            return 1;
        }
        return 0;
    }

    @Override
    public List<User> findUserByBlur(UserBlurVo blur) {
        List<User> users=null;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(blur.getInput3()!=null){
            if(blur.getSelect().equals("1")){
                queryWrapper.like("status",blur.getInput3());
                users=userMapper.selectList(queryWrapper);
                return users;
            }
            if(blur.getSelect().equals("2")){
                queryWrapper.like("username",blur.getInput3());
                users= userMapper.selectList(queryWrapper);
                queryWrapper.like("id",Integer.parseInt(blur.getInput3()));
                return users;
            }
            if(blur.getSelect().equals("3")){
                System.out.println(123);
                queryWrapper.like("id",Integer.parseInt(blur.getInput3()));
                users=userMapper.selectList(queryWrapper);
                return users;
            }

            queryWrapper.like("username",blur.getInput3());
            users= userMapper.selectList(queryWrapper);

        }
        return users;
    }

    @Override
    public int updatePreventUserById(User user) {
        user.setStatus("已禁用");
        int i = userMapper.updateById(user);
        if(i>0){
            return 1;
        }
        return 0;
    }
}
