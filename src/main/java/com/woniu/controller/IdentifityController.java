package com.woniu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.domin.Identifity;
import com.woniu.dto.Result;
import com.woniu.dto.StatusCode;
import com.woniu.mapper.IdentifityMapper;
import com.woniu.service.IdentifityService;
import com.woniu.vo.IdentifityVo;
import com.woniu.vo.PageVO;
import com.woniu.vo.ToIdentifityVo;
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
public class IdentifityController {

    @Resource
    private IdentifityService identifityService;
    //分页查询全部
    @GetMapping("/test")
    public Result querypro(PageVO pageVO){
        Page<IdentifityVo> page1 = new Page<>(pageVO.getCurrent(),pageVO.getSizePage());
        IPage<IdentifityVo> pageAll = identifityService.getPageAll(page1);
        //List<IdentifityVo> records = page.getRecords();
        System.out.println(pageAll+"------------------------");
        return new Result(true, StatusCode.OK,"查询成功",pageAll);
    }
    //分页条件查询
    @GetMapping("/pro")
    public Result queryProduct(IdentifityVo identifityVo){
        System.out.println(identifityVo+"前端的值"+identifityVo.getCurrent());

        IPage<IdentifityVo> voPage = identifityService.queryConditional(identifityVo);


        return new Result(true, StatusCode.OK,"查询成功",voPage);
    }


    //删除
    @PostMapping("/deletepro")
    public Result deleteidentifity(@RequestBody Identifity identifity ){

        int i = identifityService.deleteIdentifity(identifity);
        System.out.println(i+"sssssssssss");
        if (i>0){
            return  new Result(true,StatusCode.OK,"删除成功");
        }
        return  new Result(false,StatusCode.ERROR,"删除失败");
    }

}

