package com.woniu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.domin.ProductAttribute;
import com.woniu.dto.Result;
import com.woniu.vo.CheckVO;
import com.woniu.vo.PageVO;


public interface ProductAttributeService extends IService<ProductAttribute> {
    //查询二级商品小类
    Page queryProductAttrAllInfo(PageVO pageVO) throws Exception;

    //模糊查询商品大类
    Page queryProductAttrLikeInfo(CheckVO checkVO) throws Exception;

    //新增商品信息
    Result insertProductAttrInfo(ProductAttribute productAttribute) throws Exception;

    //修改商品信息
    Result updataProductAttrInfo(ProductAttribute productAttribute) throws Exception;

    //删除商品信息
    Result deleteProductAttrInfo(ProductAttribute productAttribute) throws Exception;

    //鉴定完成后，修改商品状态
    int updateProductStatus(ProductAttribute productAttribute);
}
