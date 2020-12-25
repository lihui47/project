package com.woniu.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.woniu.domin.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.dto.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author team
 * @since 2020-12-21
 */
public interface PermissionService extends IService<Permission> {

    /**
     * Query permission name list.
     *
     * @param username the username
     * @return the list
     */
    Result<Object> queryPermissionName(String username);

}
