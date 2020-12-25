package com.woniu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.woniu.domin.Second;
import com.woniu.dto.Result;
import com.woniu.dto.StatusCode;
import com.woniu.service.SecondService;
import com.woniu.vo.CheckVO;
import com.woniu.vo.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sun
 * @since 2020-12-22
 */
@RestController
@RequestMapping("/second")
@Api(tags = {"分类管理controller"})
public class SecondController {

    @Resource
    private SecondService secondService;

    @GetMapping("querySecond")
    @ApiOperation(value = "分类查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "PageVO", value = "封装的页面数据"),
            @ApiImplicitParam(name = "current", value = "当前页码"),
            @ApiImplicitParam(name = "id", value = "模糊查询数据的id"),
            @ApiImplicitParam(name = "name", value = "模糊查询的名称"),
            @ApiImplicitParam(name = "sizePage", value = "展示的条数")

    })
    private Result querySecondAllInfo(PageVO pageVO) throws Exception {
        Page page = secondService.querySecondAllInfo(pageVO);
        return new Result(true, StatusCode.OK,"分页查询成功",page);
    }

    @GetMapping("likeSecond")
    @ApiOperation(value = "分类模糊查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "CheckVO", value = "封装的页面数据"),
            @ApiImplicitParam(name = "current", value = "当前页码"),
            @ApiImplicitParam(name = "id", value = "模糊查询数据的id"),
            @ApiImplicitParam(name = "name", value = "模糊查询的名称"),
            @ApiImplicitParam(name = "sizePage", value = "展示的条数")

    })
    public Result queryFirstInfo(CheckVO checkVO)throws Exception {
        Page page = secondService.queryLikeInfo(checkVO);
        return new Result(true,StatusCode.OK,"模糊查询成功",page);
    }

    @PostMapping("insertSecond")
    @ApiOperation(value = "新增分类")
    @ApiImplicitParam(name = "Second", value = "封装分类的数据")
    public Result insertSecondInfo(@RequestBody Second second) throws Exception {
        Result result = secondService.insertSecondInfo(second);
        return result;
    }

    @PostMapping("updataSecond")
    @ApiOperation(value = "修改分类")
    @ApiImplicitParam(name = "First", value = "封装分类的数据")
    public Result updataSecondInfo(@RequestBody Second second)throws Exception {
        Result result = secondService.updataSecoondInfo(second);
        return result;
    }

    @PostMapping("deleteSecond")
    @ApiOperation(value = "删除大类")
    @ApiImplicitParam(name = "First", value = "封装分类的数据")
    public Result deleteSecondInfo(@RequestBody Second second) throws Exception {
        Result result = secondService.deleteSecondInfo(second);
        return result;
    }

    //大类筛选的方法
    @GetMapping("checkFirst")
    @ApiOperation(value = "大类筛选功能")
    public Result checkFirtstInfo() throws Exception {
        Result result = secondService.querySecondAndFirst();
        return result;
    }

    //根据大类id返回小类信息
    @GetMapping("checkFirstId")
    @ApiOperation(value = "根据大类id查询分类信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "CheckVO", value = "封装的页面数据"),
            @ApiImplicitParam(name = "current", value = "当前页码"),
            @ApiImplicitParam(name = "id", value = "模糊查询数据的id"),
            @ApiImplicitParam(name = "name", value = "模糊查询的名称"),
            @ApiImplicitParam(name = "sizePage", value = "展示的条数")

    })
    public Result checkFirstInfoId(CheckVO checkVO) throws Exception {
        System.out.println(checkVO);
        Result result = secondService.querySecondInfoByFirstId(checkVO);
        return result;
    }

}

