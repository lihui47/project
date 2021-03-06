package com.woniu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.domin.First;
import com.woniu.dto.Result;
import com.woniu.vo.CheckVO;
import com.woniu.vo.PageVO;


/**
 * <p>
 *  业务层服务类
 * </p>
 *
 * @author sun
 * @since 2020-12-22
 */
public interface FirstService extends IService<First> {
    //查询一级商品大类
    Page queryFirstAllInfo(PageVO pageVOe) throws Exception;

    //模糊查询商品大类
    Page queryLikeInfo(CheckVO checkVO) throws Exception;

    //新增商品大类
    Result insertFirstInfo(First first) throws Exception;

    //修改商品大类
    Result updataFirstInfo(First first)throws Exception;

    //删除商品大类
    Result deleteFirstInfo(First first)throws Exception;

}
