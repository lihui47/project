package com.woniu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.woniu.domin.First;
import com.woniu.dto.Result;
import com.woniu.dto.StatusCode;
import com.woniu.service.FirstService;
import com.woniu.vo.CheckVO;
import com.woniu.vo.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  商品大类前端控制器
 * </p>
 *
 * @author sun
 * @since 2020-12-22
 */
@RestController
@RequestMapping("/first")
@Api(tags = {"大类管理controller"})
public class FirstController {
    @Resource
    private FirstService firstService;

    @ApiOperation(value = "大类查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "PageVO", value = "封装的页面数据"),
            @ApiImplicitParam(name = "current", value = "当前页码"),
            @ApiImplicitParam(name = "id", value = "模糊查询数据的id"),
            @ApiImplicitParam(name = "name", value = "模糊查询的名称"),
            @ApiImplicitParam(name = "sizePage", value = "展示的条数")

    })
    @GetMapping("queryFirst")
    public Result queryFirstInfo(PageVO pageVO) throws Exception {
        Page page = firstService.queryFirstAllInfo(pageVO);
        List records = page.getRecords();
        System.out.println(records);
        return new Result(true, StatusCode.OK,"分页查询成功",page);
    }

    @GetMapping("likeFirst")
    @ApiOperation(value = "大类模糊查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "CheckVO", value = "封装的页面数据"),
            @ApiImplicitParam(name = "current", value = "当前页码"),
            @ApiImplicitParam(name = "id", value = "模糊查询数据的id"),
            @ApiImplicitParam(name = "name", value = "模糊查询的名称"),
            @ApiImplicitParam(name = "sizePage", value = "展示的条数")

    })
    public Result querylikeFirstInfo(CheckVO checkVO)throws Exception {
        Page page = firstService.queryLikeInfo(checkVO);
        return new Result(true,StatusCode.OK,"模糊查询成功",page);
    }

    @PostMapping("insertFirst")
    @ApiOperation(value = "新增大类")
    @ApiImplicitParam(name = "First", value = "封装大类的数据")
    public Result insertFirstInfo(@RequestBody First first)throws Exception {
        return firstService.insertFirstInfo(first);
    }

    @PostMapping("updataFirst")
    @ApiOperation(value = "修改大类")
    @ApiImplicitParam(name = "First", value = "封装大类的数据")
    public Result updataFirstInfo(@RequestBody First first)throws Exception {
        return firstService.updataFirstInfo(first);
    }

    @PostMapping("deleteFirst")
    @ApiOperation(value = "删除大类")
    @ApiImplicitParam(name = "First", value = "封装大类的数据")
    public Result deleteFirst(@RequestBody First first)throws Exception {
        return firstService.deleteFirstInfo(first);
    }

}

