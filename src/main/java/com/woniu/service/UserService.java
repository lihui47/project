package com.woniu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.domin.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.dto.Result;
import com.woniu.vo.UserBlurVo;
import com.woniu.vo.UserPageVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author team
 * @since 2020 -12-21
 */
public interface UserService extends IService<User> {
    /**
     * Register .
     *
     * @param user the user
     * @return the result
     */
    Result<Object> register(User user);

    /**
     * Login
     *
     * @param user the user
     * @return the result
     */
    Result<Object> login(User user);


    /**
     * Find all check user page.
     * 查询待审核的用户
     * @param userPageVo the user page vo
     * @return the page
     */
    Page<User> findAllCheckUser(UserPageVo userPageVo);

    /**
     * Update user by id int.
     * 通过id修改用户
     *
     * @param user the user
     * @return the int
     */
    int updateUserById(User user);

    /**
     * Update reject user by id int.
     * 驳回
     *
     * @param user the user
     * @return the int
     */
    int updateRejectUserById(User user);

    /**
     * Find all user page.
     * 分页查询所以用户
     *
     * @param userPageVo the user page vo
     * @return the page
     */
    Page<User> findAllUser(UserPageVo userPageVo);

    /**
     * Delete user by id int.
     * 删除用户
     *
     * @param id the id
     * @return the int
     */
    int deleteUserById(Integer id);

    /**
     * Find user by blur list.
     * 用户信息模糊插叙
     *
     * @param blur the blur
     * @return the list
     */

    List<User> findUserByBlur(UserBlurVo blur);

    /**
     * Update prevent user by id int.
     * 禁用用户
     *
     * @param user the user
     * @return the int
     */
    int updatePreventUserById(User user);

}
