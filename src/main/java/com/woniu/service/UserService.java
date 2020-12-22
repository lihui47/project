package com.woniu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.domin.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.vo.UserBlurVo;
import com.woniu.vo.UserPageVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author team
 * @since 2020-12-21
 */
public interface UserService extends IService<User> {
    //查询所以需要审核的用户
    List<User> findAllCheckUser();
    //审核通过用户
    int updateUserById(User user);
    //驳回用户
    int updateRejectUserById(User user);
    //分页查询所以用户
    Page findAllUser(UserPageVo userPageVo);
    //删除用户
    int deleteUserById(Integer id);
    //用户信息模糊插叙
    List<User> findUserByBlur(UserBlurVo blur);
    //禁用用户
    int updatePreventUserById(User user);
}