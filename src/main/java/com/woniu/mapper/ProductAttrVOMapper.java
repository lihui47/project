package com.woniu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.vo.ProductAttrVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ProductAttrVOMapper extends BaseMapper<ProductAttrVO> {
    @Select("SELECT\n" +
            "  p1.id,p1.name,s1.name as secondName, p1.number,p1.price,p1.status\n" +
            "FROM\n" +
            "\tt_product AS p1\n" +
            "JOIN\n" +
            "\tt_second AS s1\n" +
            "ON\n" +
            "\tp1.sid =s1.id"
            )
    Page<ProductAttrVO> productAttrPageInfo(Page<ProductAttrVO> page)throws Exception;


    @Select("SELECT\n" +
            "  p1.id,p1.name,s1.name as secondName, p1.number,p1.price,p1.status\n" +
            "FROM\n" +
            "\tt_product AS p1\n" +
            "JOIN\n" +
            "\tt_second AS s1\n" +
            "ON\n" +
            "\tp1.sid =s1.id "+
            "${ew.customSqlSegment}")
    Page<ProductAttrVO> productAttrLikePage(Page<ProductAttrVO> page, @Param(Constants.WRAPPER) QueryWrapper<ProductAttrVO> queryWrapper);
}
