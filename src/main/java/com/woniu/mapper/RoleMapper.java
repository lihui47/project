package com.woniu.mapper;

import com.woniu.domin.Permission;
import com.woniu.domin.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author team
 * @since 2020-12-21
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * Gets permissions.
     * 根据角色id查询权限
     * @param id the id
     * @return the permissions
     */
    @Select("SELECT p.permissionName\n" +
            "FROM t_role r\n" +
            "left JOIN t_permission_role pr\n" +
            "ON r.id = pr.rid\n" +
            "left JOIN t_permission p\n" +
            "ON pr.pid = p.id\n" +
            "WHERE r.id = #{id}")
    List<Permission> getPermissions(Integer id);

}
