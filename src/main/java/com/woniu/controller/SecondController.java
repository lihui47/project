package com.woniu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.woniu.domin.Second;
import com.woniu.dto.Result;
import com.woniu.dto.StatusCode;
import com.woniu.service.SecondService;
import com.woniu.vo.CheckVO;
import com.woniu.vo.PageVO;
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
public class SecondController {

    @Resource
    private SecondService secondService;

    @GetMapping("querySecond")
    private Result querySecondAllInfo(PageVO pageVO) throws Exception {
        Page page = secondService.querySecondAllInfo(pageVO);
        return new Result(true, StatusCode.OK,"分页查询成功",page);
    }

    @GetMapping("likeSecond")
    public Result queryFirstInfo(CheckVO checkVO)throws Exception {
        Page page = secondService.queryLikeInfo(checkVO);
        return new Result(true,StatusCode.OK,"模糊查询成功",page);
    }

    @PostMapping("insertSecond")
    public Result insertSecondInfo(@RequestBody Second second) throws Exception {
        Result result = secondService.insertSecondInfo(second);
        return result;
    }

    @PostMapping("updataSecond")
    public Result updataSecondInfo(@RequestBody Second second)throws Exception {
        Result result = secondService.updataSecoondInfo(second);
        return result;
    }

    @PostMapping("deleteSecond")
    public Result deleteSecondInfo(@RequestBody Second second) throws Exception {
        Result result = secondService.deleteSecondInfo(second);
        return result;
    }

    //大类筛选的方法
    @GetMapping("checkFirst")
    public Result checkFirtstInfo() throws Exception {
        Result result = secondService.querySecondAndFirst();
        return result;
    }

    //根据大类id返回小类信息
    @GetMapping("checkFirstId")
    public Result checkFirstInfoId(CheckVO checkVO) throws Exception {
        System.out.println(checkVO);
        Result result = secondService.querySecondInfoByFirstId(checkVO);
        return result;
    }

}

