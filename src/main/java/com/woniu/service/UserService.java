package com.woniu.service;

import com.woniu.domin.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.dto.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author team
 * @since 2020-12-21
 */
public interface UserService extends IService<User> {
    /**
     * Register result.
     *
     * @param user the user
     * @return the result
     */
    Result<Object> register(User user);

    /**
     * Login result.
     *
     * @param user the user
     * @return the result
     */
    Result<Object> login(User user);

}
