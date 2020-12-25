package com.woniu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.domin.Identifity;
import com.woniu.dto.Result;
import com.woniu.dto.StatusCode;
import com.woniu.mapper.IdentifityMapper;
import com.woniu.service.GoIndentifyService;
import com.woniu.service.IdentifityService;
import com.woniu.vo.IdentifityVo;
import com.woniu.vo.PageVO;
import com.woniu.vo.ToIdentifityVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author team
 * @since 2020-12-22
 */
@RestController
@RequestMapping("/identifity")
@CrossOrigin
@Api(tags = {"商品列表接口controller"})
public class IdentifityController {
    @Resource
    private GoIndentifyService goIndentifyService;
    @Resource
    private IdentifityService identifityService;
    //分页查询全部
    @GetMapping("test")
    @ApiOperation(value = "分页查询全部商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页码"),
            @ApiImplicitParam(name = "sizePage", value = "展示的条数")
    })
    public Result querypro(PageVO pageVO){
        System.out.println(pageVO);
        Page<IdentifityVo> page1 = new Page<>(pageVO.getCurrent(),pageVO.getSizePage());
        IPage<IdentifityVo> pageAll = identifityService.getPageAll(page1);
        //List<IdentifityVo> records = page.getRecords();
        System.out.println(pageAll+"------------------------");
        return new Result(true, StatusCode.OK,"查询成功",pageAll);
    }
    //分页条件查询
    @GetMapping("/pro")
    @ApiOperation(value = "分页查询条件")
    public Result queryProduct(IdentifityVo identifityVo){
        System.out.println(identifityVo+"前端的值"+identifityVo.getCurrent()+identifityVo.getSizePage());

        IPage<IdentifityVo> voPage = identifityService.queryConditional(identifityVo);

        return new Result(true, StatusCode.OK,"查询cg",voPage);
    }


    //删除
    @PostMapping("/deletepro")
    @ApiOperation(value = "删除商品鉴定信息")
    @ApiImplicitParam(name = "Identifity", value = "封装的删除信息")
    public Result deleteidentifity(@RequestBody Identifity identifity ){

        int i = goIndentifyService.deleteIdentifity(identifity);
        if (i>0){
            return  new Result(true,StatusCode.OK,"删除成功");
        }
        return  new Result(false,StatusCode.ERROR,"删除失败");
    }

    //修改鉴定
    @GetMapping("/updateIden")
    @ApiOperation(value = "修改商品鉴定信息")
    @ApiImplicitParam(name = "Identifity", value = "封装的修改信息")
    public Result updateIden(Identifity identifity){
        System.out.println(identifity+"前端的值");
        int i = identifityService.updateIdentifity(identifity);
        if(i>0){
            return new Result(true,StatusCode.OK,"修改成功");
        }
        return new Result(false,StatusCode.ERROR,"修改失败");
    }


    //查询鉴定信息
    @GetMapping("/queryident")
    @ApiOperation(value = "查询鉴定信息")
    public Result queryident(IdentifityVo identifityVo){
        System.out.println(identifityVo.getId()+"前端的ID");
        List<IdentifityVo> identifityVo1 = identifityService.getIdentifityVo(identifityVo);
        System.out.println(identifityVo1+"cccc"+identifityVo.getIprice());

        return  new Result(true,StatusCode.OK,"查询成功",identifityVo1);
    }

}

