package com.woniu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.domin.Second;
import com.woniu.dto.Result;
import com.woniu.vo.CheckVO;
import com.woniu.vo.PageVO;
;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sun
 * @since 2020-12-22
 */
public interface SecondService extends IService<Second> {
    //查询二级商品小类
    Page querySecondAllInfo(PageVO pageVO) throws Exception;

    //模糊查询商品大类
    Page queryLikeInfo(CheckVO checkVO) throws Exception;

    //新增二级商品小类
    Result insertSecondInfo(Second second)throws Exception;

    //修改二级商品小类
    Result updataSecoondInfo(Second second)throws Exception;

    //删除二级商品小类
    Result deleteSecondInfo(Second second)throws Exception;

    //查询一级大类
    Result querySecondAndFirst()throws Exception;

    //根据一级大类id 查询二级分类信息
    Result querySecondInfoByFirstId(CheckVO checkVO) throws Exception;

}
