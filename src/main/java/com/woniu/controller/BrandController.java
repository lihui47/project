package com.woniu.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.domin.Brand;
import com.woniu.dto.Result;
import com.woniu.dto.StatusCode;
import com.woniu.service.BrandService;
import com.woniu.vo.CheckVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.experimental.PackagePrivate;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author team
 * @since 2020-12-22
 */
//@CrossOrigin
@RestController
@RequestMapping("/brand")
@Api(tags = {"品牌管理controller"})
public class BrandController {

    @Resource
    private BrandService brandService;

    @GetMapping("/queryLikely")
    @ApiOperation(value = "品牌模糊查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "CheckVO", value = "封装的页面数据"),
            @ApiImplicitParam(name = "current", value = "当前页码"),
            @ApiImplicitParam(name = "id", value = "模糊查询数据的id"),
            @ApiImplicitParam(name = "name", value = "模糊查询的名称"),
            @ApiImplicitParam(name = "sizePage", value = "展示的条数")

    })
    public Result<Object> queryLikely(CheckVO checkVO){
        System.out.println(checkVO);
        Page<Brand> page = brandService.queryBrandLikely(checkVO);
        return new Result<>(true, StatusCode.OK,"模糊查询成功",page);
    }

    @ApiOperation(value = "品牌查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "CheckVO", value = "封装的页面数据"),
            @ApiImplicitParam(name = "current", value = "当前页码"),
            @ApiImplicitParam(name = "id", value = "模糊查询数据的id"),
            @ApiImplicitParam(name = "name", value = "模糊查询的名称"),
            @ApiImplicitParam(name = "sizePage", value = "展示的条数")

    })
    @GetMapping("/queryBrands")
    public Result<Object> queryBrands(CheckVO checkVO){
        Page<Brand> page = brandService.queryBrands(checkVO);
        return new Result<>(true, StatusCode.OK,"分页查询成功",page);
    }


    @PostMapping("/insert")
    @ApiOperation(value = "新增品牌")
    @ApiImplicitParam(name = "brand", value = "封装品牌信息数据")
    public Result<Object> addBrand(@RequestBody Brand brand){
        if(StringUtils.isEmpty(brand.getName())&&StringUtils.isEmpty(brand.getAbbr())) {
            return new Result<>(false, StatusCode.ERROR, "输入有误");
        }
        else{
            brandService.addBrand(brand);
            return new Result<>(true,StatusCode.OK,"新增成功");
        }
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改品牌")
    @ApiImplicitParam(name = "brand", value = "封装品牌信息数据")
    public Result<Object> update(@RequestBody Brand brand){
        if(StringUtils.isEmpty(brand.getName())&&StringUtils.isEmpty(brand.getAbbr())) {
            return new Result<>(false, StatusCode.ERROR, "输入有误");
        }
        else{
            brandService.updateBrand(brand);
            return new Result<>(true,StatusCode.OK,"修改成功");
    }}

    @GetMapping("/delete")
    @ApiOperation(value = "删除品牌")
    @ApiImplicitParam(name = "id", value = "删除数据的id")
    public Result<Object> delete(Integer id){
        brandService.deleteBrandById(id);
        return new Result<>(true,StatusCode.OK,"删除成功");
    }


}

