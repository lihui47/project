package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.domin.Warehouse;
import com.woniu.mapper.WarehouseMapper;
import com.woniu.service.WarehouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.vo.CheckVO;
import com.woniu.vo.PageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author team
 * @since 2020-12-23
 */
@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse> implements WarehouseService {
    @Resource
    private WarehouseMapper warehouseMapper;
    /*
    分页查询展示数据
     */
    @Override
    public Page findAllWareHouse(CheckVO CheckVO) {
        if(CheckVO.getName()==null){
            //说明没有进行模糊查询，查询所有的仓库
            Page<Warehouse> warehousePage = new Page<>(CheckVO.getCurrent(),CheckVO.getSizePage());
            Page<Warehouse> page = warehouseMapper.selectPage(warehousePage, null);
            return page;
        }else{
            //进行模糊查询
            Page<Warehouse> warehousePage = new Page<>();
            QueryWrapper<Warehouse> warehouseQueryWrapper = new QueryWrapper<>();
            warehouseQueryWrapper.like("name",CheckVO.getName());
            Page<Warehouse> page = warehouseMapper.selectPage(warehousePage, warehouseQueryWrapper);
            return page;
        }
    }
    /*
    根据id删除仓库数据
     */
    @Override
    public int deleteWareHouseById(Warehouse warehouse) {
        int i = warehouseMapper.deleteById(warehouse.getId());
        if(i>0){
            System.out.println("删除成功");
            return 1;
        }
        return 0;
    }
    /*
    根据id修改仓库
     */
    @Override
    public int updateWareHouseById(Warehouse warehouse) {
        int i = warehouseMapper.updateById(warehouse);
        if(i>0){
            return 1;
        }
        return 0;
    }

    @Override
    public int insertWareHouse(Warehouse warehouse) {
        int row = warehouseMapper.insert(warehouse);
        if(row>0){
            return 1;
        }
        return 0;
    }
}
