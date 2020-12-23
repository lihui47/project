package com.woniu.service;

import com.woniu.domin.User;
import com.woniu.domin.UserRoler;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author team
 * @since 2020-12-22
 */
public interface UserRolerService extends IService<UserRoler> {
    //审核通过后，赋予该用户角色
    int InsertUserRole(User user);
}
