package com.woniu.service.impl;

import com.woniu.domin.User;
import com.woniu.domin.UserRoler;

import com.woniu.mapper.UserRolerMapper;
import com.woniu.service.UserRolerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author team
 * @since 2020-12-22
 */
@Service
public class UserRolerServiceImpl extends ServiceImpl<UserRolerMapper, UserRoler> implements UserRolerService {
    @Resource
    private UserRolerMapper userRolerMapper;
    /*
    审核通过后,赋予该用户角色
     */
    @Override
    public int InsertUserRole(User user){
        System.out.println("授予角色");
        UserRoler userRole = new UserRoler();
        userRole.setRid(2);
        userRole.setUid(user.getId());
        int row = userRolerMapper.insert(userRole);
        if(row>0){
            return 1;
        }
        return 0;
    }
}
