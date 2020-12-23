package com.woniu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.woniu.vo.FirstVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface FirstVOMapper extends BaseMapper<FirstVO> {
    @Select("SELECT\n" +
            " f1.name,s1.id,s1.`name` as secondName\n" +
            "FROM\n" +
            "\tt_first as f1\n" +
            "JOIN\n" +
            "\tt_second as s1\n" +
            "ON\n" +
            "\tf1.id=s1.fid\n" +
            "${ew.customSqlSegment}")
    Page<FirstVO> querySecondAndFirstInfo(Page<FirstVO> page, @Param(Constants.WRAPPER) QueryWrapper<FirstVO> queryWrapper);
}
