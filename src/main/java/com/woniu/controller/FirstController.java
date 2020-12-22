package com.woniu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.dto.Result;
import com.woniu.dto.StatusCode;
import com.woniu.service.FirstService;
import com.woniu.vo.CheckVO;
import com.woniu.vo.PageVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sun
 * @since 2020-12-22
 */
@RestController
@RequestMapping("/first")
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

}

