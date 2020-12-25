package com.woniu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.woniu.domin.ProductAttribute;
import com.woniu.dto.Result;
import com.woniu.dto.StatusCode;
import com.woniu.service.ProductAttributeService;
import com.woniu.vo.CheckVO;
import com.woniu.vo.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = {"商品管理controller"})
public class ProductAttrController {
    @Resource
    private ProductAttributeService productAttributeService;

    @GetMapping("queryproductAttr")
    @ApiOperation(value = "商品查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "PageVO", value = "封装的页面数据"),
            @ApiImplicitParam(name = "current", value = "当前页码"),
            @ApiImplicitParam(name = "id", value = "模糊查询数据的id"),
            @ApiImplicitParam(name = "name", value = "模糊查询的名称"),
            @ApiImplicitParam(name = "sizePage", value = "展示的条数")

    })
    public Result queryProductAttrAllInfo(PageVO pageVO) throws Exception {
        Page page = productAttributeService.queryProductAttrAllInfo(pageVO);
        return new Result(true, StatusCode.OK,"分页查询成功",page);
    }

    @GetMapping("likeproductAttr")
    @ApiOperation(value = "商品模糊查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "CheckVO", value = "封装的页面数据"),
            @ApiImplicitParam(name = "current", value = "当前页码"),
            @ApiImplicitParam(name = "id", value = "模糊查询数据的id"),
            @ApiImplicitParam(name = "name", value = "模糊查询的名称"),
            @ApiImplicitParam(name = "sizePage", value = "展示的条数")

    })
    public Result queryProductAttrLikeInfo(CheckVO checkVO)throws Exception {
        Page page = productAttributeService.queryProductAttrLikeInfo(checkVO);
        return new Result(true, StatusCode.OK,"模糊查询成功",page);
    }

    @PostMapping("insertproductAttr")
    @ApiOperation(value = "新增商品")
    @ApiImplicitParam(name = "ProductAttribute", value = "封装商品的数据")
    public Result insertProductAttr(@RequestBody ProductAttribute productAttribute) throws Exception {
        Result result = productAttributeService.insertProductAttrInfo(productAttribute);
        return result;
    }

    @PostMapping("updataproductAttr")
    @ApiOperation(value = "修改商品")
    @ApiImplicitParam(name = "ProductAttribute", value = "封装商品的数据")
    public Result updataproductAttr(@RequestBody ProductAttribute productAttribute)throws Exception {
        Result result = productAttributeService.updataProductAttrInfo(productAttribute);
        return result;
    }

    @PostMapping("deleteproductAttr")
    @ApiOperation(value = "删除商品")
    @ApiImplicitParam(name = "ProductAttribute", value = "封装商品的数据")
    public Result deleteproductAttrInfo(@RequestBody ProductAttribute productAttribute)throws Exception {
        Result result = productAttributeService.deleteProductAttrInfo(productAttribute);
        return result;
    }
}
