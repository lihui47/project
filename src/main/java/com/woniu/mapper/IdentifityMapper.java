package com.woniu.mapper;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.domin.Identifity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.vo.IdentifityVo;
import com.woniu.vo.ToIdentifityVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;



import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author team
 * @since 2020-12-22
 */
public interface IdentifityMapper extends BaseMapper<IdentifityVo> {
    @Select("SELECT i.id,p.`name`,s.sname,i.writer,i.identitier,i.`status`,i.gmt_create  " +
            "FROM t_identifity i  " +
            "JOIN t_product p  " +
            "ON i.pid=p.id  " +
            "JOIN t_second s  " +
            "ON p.sid=s.id")
    Page<IdentifityVo> getPageAll(Page<IdentifityVo> identifityVoPage);
    //List<IdentifityVo> queryAll(Page page);

    @Select("SELECT i.id,p.`name`,s.sname,i.writer,i.identitier,i.`status`,i.gmt_create\n" +
            "FROM t_identifity i\n" +
            "JOIN t_product p\n" +
            "ON i.pid=p.id\n" +
            "JOIN t_second s\n" +
            "ON p.sid=s.id\n" +
            "${ew.customSqlSegment}")
    Page<IdentifityVo> getConditional(Page<IdentifityVo> identifityVoPage,@Param(Constants.WRAPPER) QueryWrapper<IdentifityVo> queryWrapper);
    //List<IdentifityVo> queryConditional(Page page);




}
