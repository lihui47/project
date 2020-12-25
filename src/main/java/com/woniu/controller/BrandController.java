package com.woniu.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.domin.Brand;
import com.woniu.dto.Result;
import com.woniu.dto.StatusCode;
import com.woniu.service.BrandService;
import com.woniu.vo.CheckVO;
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
@CrossOrigin
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Resource
    private BrandService brandService;

    @GetMapping("/queryLikely")
    public Result<Object> queryLikely(CheckVO checkVO){
        System.out.println(checkVO);
        Page<Brand> page = brandService.queryBrandLikely(checkVO);
        return new Result<>(true, StatusCode.OK,"模糊查询成功",page);
    }
    @GetMapping("/queryBrands")
    public Result<Object> queryBrands(CheckVO checkVO){
        Page<Brand> page = brandService.queryBrands(checkVO);
        return new Result<>(true, StatusCode.OK,"分页查询成功",page);
    }


    @PostMapping("/insert")
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
    public Result<Object> update(@RequestBody Brand brand){
        if(StringUtils.isEmpty(brand.getName())&&StringUtils.isEmpty(brand.getAbbr())) {
            return new Result<>(false, StatusCode.ERROR, "输入有误");
        }
        else{
            brandService.updateBrand(brand);
            return new Result<>(true,StatusCode.OK,"修改成功");
    }}

    @PostMapping("/deleteBrand")
    public Result<Object> delete(@RequestBody Brand brand){
        brandService.deleteBrandById(brand.getId());
        return new Result<>(true,StatusCode.OK,"删除成功");
    }


}

