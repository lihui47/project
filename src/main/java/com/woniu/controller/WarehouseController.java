package com.woniu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.domin.Warehouse;
import com.woniu.dto.Result;
import com.woniu.dto.StatusCode;
import com.woniu.service.UserService;
import com.woniu.service.WarehouseService;
import com.woniu.vo.CheckVO;
import com.woniu.vo.PageVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author team
 * @since 2020-12-23
 */
@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Resource
    private WarehouseService warehouseService;
    /*
    分页展示仓库数据
     */
    @GetMapping("showHouse")
    public Result showWareHouse(CheckVO checkVO){
        System.out.println(checkVO);
        Page page=warehouseService.findAllWareHouse(checkVO);
        return new Result(true, StatusCode.OK,"仓库信息查询成功",page);
    }
    /*
    根据id删除仓库
     */
    @PostMapping("deleteHouse")
    public Result deleteWareHouse(@RequestBody Warehouse warehouse){
       int row=warehouseService.deleteWareHouseById(warehouse);
        return new Result(true,StatusCode.OK,"删除成功");
    }
    /*
    根据id修改仓库
     */
    @PostMapping("updateHouse")
    public Result updateWareHouse(@RequestBody Warehouse warehouse){
        System.out.println(warehouse );
        int row=warehouseService.updateWareHouseById(warehouse);
        if(row>0){
            return new Result(true,StatusCode.OK,"仓库修改成功");
        }
        return new Result(true,StatusCode.ERROR,"仓库修改失败");
    }
    /*
    插入仓库信息
     */
    @PostMapping("insertHouse")
    public Result insertWareHouse(@RequestBody Warehouse warehouse){
        System.out.println(warehouse);
        int row=warehouseService.insertWareHouse(warehouse);
        if(row>0){
            return new Result(true,StatusCode.OK,"仓库信息插入成功");
        }
        return new Result(true,StatusCode.OK,"仓库信息插入失败");
    }


}

