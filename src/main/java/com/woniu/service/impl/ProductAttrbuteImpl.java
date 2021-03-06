package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.domin.Identifity;
import com.woniu.domin.ProductAttribute;
import com.woniu.domin.Second;
import com.woniu.dto.Result;
import com.woniu.dto.StatusCode;
import com.woniu.mapper.IdentifityInsertMapper;
import com.woniu.mapper.ProductAttrVOMapper;
import com.woniu.mapper.ProductAttributeMapper;
import com.woniu.mapper.SecondMapper;
import com.woniu.service.ProductAttributeService;
import com.woniu.vo.CheckVO;
import com.woniu.vo.PageVO;
import com.woniu.vo.ProductAttrVO;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName ProductAttrImpl
 * @Description TODO
 * @Authro SheepSun
 * @Date 2020/12/23 18:51
 * @Version 1.0
 **/

@Service("productAttrbute")
public class ProductAttrbuteImpl extends ServiceImpl<ProductAttributeMapper, ProductAttribute> implements ProductAttributeService {
    @Resource
    private ProductAttributeMapper productAttributeMapper;
    @Resource
    private ProductAttrVOMapper productAttrVOMapper;
    @Resource
    private SecondMapper secondMapper;
    @Resource
    private RedisTemplate<Object, Object> redis;
    @Resource
    private IdentifityInsertMapper identifityInsertMapper;

    @Resource
    private Identifity identifity;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Page queryProductAttrAllInfo(PageVO pageVO) throws Exception {

        Page<ProductAttrVO> page = new Page<>(pageVO.getCurrent(),pageVO.getSizePage());
        //设置redis缓存
        ListOperations<Object, Object> redisList = redis.opsForList();

            System.out.println("从数据库查询");
            productAttrVOMapper.productAttrPageInfo(page);
            List<ProductAttrVO> records = page.getRecords();
            for (ProductAttrVO record : records) {
                //redis存储
                if(record.getStatus().equals("未鉴定")){
                    record.setStatusName("未鉴定");
                }
            }
        return page;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Page queryProductAttrLikeInfo(CheckVO checkVO) throws Exception {
        Page<ProductAttrVO> page = new Page<>(checkVO.getCurrent(),checkVO.getSizePage());
        QueryWrapper<ProductAttrVO> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("p1.name",checkVO.getName());
        productAttrVOMapper.productAttrLikePage(page, queryWrapper);
        List<ProductAttrVO> records = page.getRecords();
        for (ProductAttrVO record : records) {
            if(record.getStatus().equals("未鉴定")){
                record.setStatusName("未鉴定");
            }
        }
        return page;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Result insertProductAttrInfo(ProductAttribute productAttribute) throws Exception {
        if (!ObjectUtils.isEmpty(productAttribute)){
            if(productAttribute.getStatusName().equals("未鉴定")){
                productAttribute.setStatus("未鉴定");
            }
            boolean b =true;
            List<ProductAttribute> productAttributes = productAttributeMapper.selectList(null);
            for (ProductAttribute attribute : productAttributes) {
                if(attribute.getName().equals(productAttribute.getName())){
                    b=false;
                }
            }
            QueryWrapper<Second> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name",productAttribute.getSecondName());
            Second second = secondMapper.selectOne(queryWrapper);
            if(b&&!ObjectUtils.isEmpty(second)){
                productAttribute.setSid(second.getId());
                productAttributeMapper.insert(productAttribute);
                //设置商品列表的操作人
                identifity.setPid(productAttribute.getId()).setWriter(productAttribute.getUserName()).setStatus(productAttribute.getStatus());
                identifityInsertMapper.insert(identifity);
                return new Result(true, StatusCode.OK,"新增成功");
            }
        }
        return null;
    }

    @Override
    public Result updataProductAttrInfo(ProductAttribute productAttribute) throws Exception {
        if(!ObjectUtils.isEmpty(productAttribute)){
            QueryWrapper<Second> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name",productAttribute.getSecondName());
            Second second = secondMapper.selectOne(queryWrapper);
            if(!ObjectUtils.isEmpty(second)){
                productAttribute.setSid(second.getId());
                QueryWrapper<ProductAttribute> updateWrapper = new QueryWrapper<>();
                updateWrapper.eq("id",productAttribute.getId());
                if(productAttribute.getStatusName().equals("未鉴定")){
                    productAttribute.setStatus("未鉴定");
                    productAttributeMapper.update(productAttribute, updateWrapper);
                    return new Result(true,StatusCode.OK,"修改成功");
                }
            }
        }
        return null;
    }

    @Override
    public Result deleteProductAttrInfo(ProductAttribute productAttribute) throws Exception {
        
        if(!ObjectUtils.isEmpty(productAttribute)){
            QueryWrapper<ProductAttribute> wrapper = new QueryWrapper<>();
            wrapper.eq("id",productAttribute.getId());
            productAttributeMapper.delete(wrapper);
            return new Result(true,StatusCode.OK,"删除成功");
        }
        return null;
    }

    @Override
    public int updateProductStatus(ProductAttribute productAttribute) {
        int update = productAttributeMapper.updateById(productAttribute);
        if(update>0){
            return 1;
        }
        return 0;
    }


}
