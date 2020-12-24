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

//查询全部
public interface IdentifityMapper extends BaseMapper<IdentifityVo> {
    @Select("SELECT i.id,p.`name`,s.`name` sname,i.writer,i.identitier,i.`status`,i.gmt_create,p.price  " +
            "FROM t_identifity i  " +
            "JOIN t_product p  " +
            "ON i.pid=p.id  " +
            "JOIN t_second s  " +
            "ON p.sid=s.id")
    Page<IdentifityVo> getPageAll(Page<IdentifityVo> identifityVoPage);
    //List<IdentifityVo> queryAll(Page page);
   //条件查询
    @Select("SELECT i.id,p.`name`,s.`name` sname,p.price,i.writer,i.identitier,i.`status`,i.gmt_create\n" +
            "FROM t_identifity i\n" +
            "JOIN t_product p\n" +
            "ON i.pid=p.id\n" +
            "JOIN t_second s\n" +
            "ON p.sid=s.id\n" +
            "${ew.customSqlSegment}")
    Page<IdentifityVo> getConditional(Page<IdentifityVo> identifityVoPage,@Param(Constants.WRAPPER) QueryWrapper<IdentifityVo> queryWrapper);
    //List<IdentifityVo> queryConditional(Page page);

    //查询鉴定信息
    @Select("SELECT i.id,i.status ,i.identitier,s.`name` sanme,p.`name`,p.price,i.newold,i.indentifyresult,i.identify_price  " +
            "FROM t_identifity i  " +
            "JOIN t_product p  " +
            "ON i.pid=p.id  " +
            "JOIN t_second s  " +
            "on s.id=p.sid  " +
            "${ew.customSqlSegment}")
    List<IdentifityVo> getIdentifity(IdentifityVo identifityVo,@Param(Constants.WRAPPER) QueryWrapper<IdentifityVo> queryWrapper);



}
