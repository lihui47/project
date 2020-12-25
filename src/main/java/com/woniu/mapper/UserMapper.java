package com.woniu.mapper;

import com.woniu.domin.Role;
import com.woniu.domin.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author team
 * @since 2020 -12-21
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * Find roles by id list.
     *
     * @param username the u id
     * @return the list
     */
    @Select("SELECT r.name\n" +
            "FROM t_user u\n" +
            "LEFT JOIN t_user_role ur\n" +
            "ON u.id = ur.id\n" +
            "LEFT JOIN t_role r\n" +
            "on ur.id = r.id\n" +
            "WHERE u.username =#{username};")
    List<Role> findRoles(String username);

}
