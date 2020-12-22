package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.domin.Brand;
import com.woniu.mapper.BrandMapper;
import com.woniu.service.BrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.vo.CheckVO;
import com.woniu.vo.PageVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author team
 * @since 2020-12-22
 */
@Service
@Transactional
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

    @Resource
    private BrandMapper brandMapper;

    @Override
    public int addBrand(Brand brand) {
        return brandMapper.insert(brand);
    }

    @Override
    public int updateBrand(Brand brand) {
        return brandMapper.updateById(brand);
    }

    @Override
    public int deleteBrandById(Integer id) {
        return brandMapper.deleteById(id);
    }

    @Override
    public Page<Brand> queryBrandLikely(CheckVO checkVO) {
        QueryWrapper<Brand> wrapper = new QueryWrapper<>();
        wrapper.like("name",checkVO.getName());
        return brandMapper.selectPage(new Page<Brand>(checkVO.getCurrent(), checkVO.getCurrent()), wrapper);
    }
}
