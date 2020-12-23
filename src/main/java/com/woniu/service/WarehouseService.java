package com.woniu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.domin.Warehouse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.vo.CheckVO;
import com.woniu.vo.PageVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author team
 * @since 2020-12-23
 */
public interface WarehouseService extends IService<Warehouse> {
    //分页查询仓库
    Page findAllWareHouse(CheckVO checkVO);
    //根据id删除仓库
    int deleteWareHouseById(Warehouse warehouse);
    //根据id修改仓库
    int updateWareHouseById(Warehouse warehouse);
    //插入仓库信息
    int insertWareHouse(Warehouse warehouse);
}
