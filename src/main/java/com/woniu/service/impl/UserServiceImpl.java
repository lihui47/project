package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.domin.User;
import com.woniu.mapper.UserMapper;
import com.woniu.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.vo.UserBlurVo;
import com.woniu.vo.UserPageVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
@Resource
private UserMapper userMapper;
/*
查询所以需要审核的员工
 */
    @Override
    public List<User> findAllCheckUser() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status","已申请");
        List<User> users = userMapper.selectList(queryWrapper);
        return users;
    }
    /*
    审核通过
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
/*
驳回方法
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
    /*
   查询所有用户，用于展示
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
