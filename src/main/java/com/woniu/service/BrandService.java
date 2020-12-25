package com.woniu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.domin.Brand;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.vo.CheckVO;
import com.woniu.vo.PageVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author team
 * @since 2020-12-22
 */
public interface BrandService extends IService<Brand> {

    /**
     * Add brand int.
     * 新增品牌
     * @param brand the brand
     * @return the int
     */
    int addBrand(Brand brand);

    /**
     * Update brand int.
     * 修改品牌
     * @param brand the brand
     * @return the int
     */
    int updateBrand(Brand brand);

    /**
     * Delete brand by id int.
     * 删除品牌
     * @param id the id
     * @return the int
     */
    int deleteBrandById(Integer id);

    /**
     * Query brand likely page.
     * 模糊查询品牌
     * @param checkVO the page vo
     * @return the page
     */
    Page<Brand> queryBrandLikely(CheckVO checkVO);

    /**
     * Query brands page.
     * 分页查询品牌
     * @param checkVO the check vo
     * @return the page
     */
    Page<Brand> queryBrands(CheckVO checkVO);
}
