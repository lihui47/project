package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.domin.Identifity;
import com.woniu.domin.ProductAttribute;
import com.woniu.mapper.GoIndentifyMapper;
import com.woniu.mapper.IdentifityMapper;
import com.woniu.mapper.ProductAttributeMapper;
import com.woniu.service.GoIndentifyService;
import com.woniu.service.IdentifityService;
import com.woniu.vo.IdentifityVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class GoIndentifyServiceImpl extends ServiceImpl<GoIndentifyMapper, Identifity> implements GoIndentifyService {
    @Resource
    private GoIndentifyMapper goIndentifyMapper;
    @Resource
    private ProductAttributeMapper productAttributeMapper;
    @Override
    public int insertToUPdateIdentify(Identifity identifity) {
        int insert = goIndentifyMapper.updateById(identifity);
        if(insert>0){
            return 1;
        }
        return 0;
    }

    @Override
    public ProductAttribute selectProductById(Integer pid) {

        QueryWrapper<ProductAttribute> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",pid);
        ProductAttribute productAttribute = productAttributeMapper.selectOne(queryWrapper);
        return productAttribute;
    }

    @Override
    public Identifity selectIdentifyById(Integer id) {
        QueryWrapper<Identifity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        System.out.println(id);
        Identifity identifity = goIndentifyMapper.selectOne(queryWrapper);
        System.out.println(identifity);
        return identifity;
    }
    //删除
    @Override
    public int deleteIdentifity(Identifity identifity) {

        int i = goIndentifyMapper.deleteById(identifity);
        return i;
    }
}
