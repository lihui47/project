package com.woniu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.woniu.domin.ProductAttribute;
import com.woniu.dto.Result;
import com.woniu.dto.StatusCode;
import com.woniu.service.ProductAttributeService;
import com.woniu.vo.CheckVO;
import com.woniu.vo.PageVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName ProductAttrController
 * @Description TODO
 * @Authro SheepSun
 * @Date 2020/12/23 18:53
 * @Version 1.0
 **/
@RestController
@RequestMapping("/productAttr")
public class ProductAttrController {
    @Resource
    private ProductAttributeService productAttributeService;

    @GetMapping("queryproductAttr")
    public Result queryProductAttrAllInfo(PageVO pageVO) throws Exception {
        Page page = productAttributeService.queryProductAttrAllInfo(pageVO);
        return new Result(true, StatusCode.OK,"分页查询成功",page);
    }

    @GetMapping("likeproductAttr")
    public Result queryProductAttrLikeInfo(CheckVO checkVO)throws Exception {
        Page page = productAttributeService.queryProductAttrLikeInfo(checkVO);
        return new Result(true, StatusCode.OK,"模糊查询成功",page);
    }

    @PostMapping("insertproductAttr")
    public Result insertProductAttr(@RequestBody ProductAttribute productAttribute) throws Exception {
        Result result = productAttributeService.insertProductAttrInfo(productAttribute);
        return result;
    }

    @PostMapping("updataproductAttr")
    public Result updataproductAttr(@RequestBody ProductAttribute productAttribute)throws Exception {
        Result result = productAttributeService.updataProductAttrInfo(productAttribute);
        return result;
    }

    @PostMapping("deleteproductAttr")
    public Result deleteproductAttrInfo(@RequestBody ProductAttribute productAttribute)throws Exception {
        Result result = productAttributeService.deleteProductAttrInfo(productAttribute);
        return result;
    }
}
