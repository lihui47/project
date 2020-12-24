package com.woniu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.domin.Identifity;
import com.woniu.mapper.IdentifityMapper;
import com.woniu.service.IdentifityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.vo.IdentifityVo;
import com.woniu.vo.PageVO;
import com.woniu.vo.ToIdentifityVo;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
public class IdentifityServiceImpl extends ServiceImpl<IdentifityMapper, IdentifityVo> implements IdentifityService {

    @Resource
    private IdentifityMapper identifityMapper;


//    @Override
//    public Page queryAll(PageVO pageVO) {
//
//        Page<IdentifityVo> page = new Page<>(pageVO.getCurrent(), pageVO.getSizePage());
//        List<IdentifityVo> records = identifityMapper.queryAll(page);
//        Page<IdentifityVo> voPage = page.setRecords(records);
//        System.out.println(records+"sssssss");
//
//        return voPage;
//    }

    @Override
    public Page<IdentifityVo> getPageAll(Page<IdentifityVo> identifityVoPage) {

        Page<IdentifityVo> pageAll = identifityMapper.getPageAll(identifityVoPage);

        return pageAll;
    }

    @Override
    public Page<IdentifityVo> queryConditional(IdentifityVo identifityVo) {

        QueryWrapper<IdentifityVo> queryWrapper = new QueryWrapper<>();
        if(!ObjectUtils.isEmpty(identifityVo.getStatus())){
            queryWrapper.eq("i.status", identifityVo.getStatus());
        }
        if(!ObjectUtils.isEmpty(identifityVo.getName())){
            queryWrapper.eq("i.status", identifityVo.getName());
        }
        if(!ObjectUtils.isEmpty(identifityVo.getIdentitier())){
            queryWrapper.eq("i.status", identifityVo.getIdentitier());
        }
        if(!ObjectUtils.isEmpty(identifityVo.getGmt_create())){
            queryWrapper.eq("i.status", identifityVo.getGmt_create());
        }
        Page<IdentifityVo> page = new Page<>(identifityVo.getCurrent(),identifityVo.getSizePage());
        IPage<IdentifityVo> voPage = identifityMapper.selectPage(page, queryWrapper);

        return (Page)voPage;
    }

    @Override
    public int deleteIdentifity(Identifity identifity) {

        int i = identifityMapper.deleteById(identifity);
        return i;
    }
    /*
    鉴定完成后，插入数据
     */
    @Override
    public int insertIdentify(IdentifityVo identifityVo) {
        int insert = identifityMapper.insert(identifityVo);

        return 0;
    }


}
