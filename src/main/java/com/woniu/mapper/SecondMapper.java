package com.woniu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.woniu.domin.Second;
import com.woniu.vo.FirstVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sun
 * @since 2020-12-22
 */
public interface SecondMapper extends BaseMapper<Second> {
    @Select("SELECT\n" +
            " f1.id,f1.name,s1.id,s1.`name` as secondName\n" +
            "FROM\n" +
            "\tt_first as f1\n" +
            "JOIN\n" +
            "\tt_second as s1\n" +
            "ON\n" +
            "\tf1.id=s1.fid\n" +
            "WHERE\n" +
            "  s1.`name`\n" +
            "LIKE \n" +
            "\t \"%${secondName}%\"")
    List<FirstVO> querySecondAndFirstInfo(Page page, String secondName);

}
