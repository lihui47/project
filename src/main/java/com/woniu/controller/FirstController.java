package com.woniu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.woniu.domin.First;
import com.woniu.dto.Result;
import com.woniu.dto.StatusCode;
import com.woniu.service.FirstService;
import com.woniu.vo.CheckVO;
import com.woniu.vo.PageVO;
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
@CrossOrigin
public class FirstController {
    @Resource
    private FirstService firstService;

    @GetMapping("queryFirst")
    public Result queryFirstInfo(PageVO pageVO) throws Exception {
        Page page = firstService.queryFirstAllInfo(pageVO);
        List records = page.getRecords();
        System.out.println(records);
        return new Result(true, StatusCode.OK,"分页查询成功",page);
    }

    @GetMapping("likeFirst")
    public Result queryFirstInfo(CheckVO checkVO)throws Exception {
        Page page = firstService.queryLikeInfo(checkVO);
        return new Result(true,StatusCode.OK,"模糊查询成功",page);
    }

    @PostMapping("insertFirst")
    public Result insertFirstInfo(@RequestBody First first)throws Exception {
        return firstService.insertFirstInfo(first);
    }

    @PostMapping("updataFirst")
    public Result updataFirstInfo(@RequestBody First first)throws Exception {
        return firstService.updataFirstInfo(first);
    }

    @PostMapping("deleteFirst")
    public Result deleteFirst(@RequestBody First first)throws Exception {
        return firstService.deleteFirstInfo(first);
    }

}

