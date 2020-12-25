package com.woniu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.domin.Identifity;
import com.woniu.mapper.GoIndentifyMapper;
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

    @Resource
    private GoIndentifyMapper goIndentifyMapper;

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
    //查询所有
    @Override
    public Page<IdentifityVo> getPageAll(Page<IdentifityVo> identifityVoPage) {

        Page<IdentifityVo> pageAll = identifityMapper.getPageAll(identifityVoPage);

        return pageAll;
    }
    //条件查询
    @Override
    public Page<IdentifityVo> queryConditional(IdentifityVo identifityVo) {
        System.out.println(identifityVo+"业务层");
        QueryWrapper<IdentifityVo> queryWrapper = new QueryWrapper<>();
        if(!ObjectUtils.isEmpty(identifityVo.getStatus())){
            System.out.println(identifityVo.getStatus());
            queryWrapper.eq("i.status", identifityVo.getStatus());
            System.out.println("状态不为空");
        }
        if(!ObjectUtils.isEmpty(identifityVo.getName())){
            queryWrapper.eq("p.name", identifityVo.getName());
            System.out.println("商品名不为空");
        }
        if(!ObjectUtils.isEmpty(identifityVo.getIdentitier())){
            queryWrapper.eq("i.identitier", identifityVo.getIdentitier());
            System.out.println("操作人不为空");
        }
        if(!ObjectUtils.isEmpty(identifityVo.getGmt_create())){
            queryWrapper.eq("i.gmt_create", identifityVo.getGmt_create());
            System.out.println("时间不为空");
        }
        Page<IdentifityVo> page = new Page<>(identifityVo.getCurrent(),3);
        IPage<IdentifityVo> voPage = identifityMapper.getConditional(page,queryWrapper);
        System.out.println(page+"www");
        System.out.println(voPage.getSize()+"ddd");
        return (Page) voPage;
    }

    /*
    鉴定完成后，插入数据
     */
    @Override
    public int insertIdentify(IdentifityVo identifityVo) {
        int insert = identifityMapper.insert(identifityVo);

        return 0;
    }
    //查询鉴定信息
    @Override
    public List<IdentifityVo> getIdentifityVo(IdentifityVo identifityVo) {
        System.out.println(identifityVo+"前端信息");
        QueryWrapper<IdentifityVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("i.id",identifityVo.getId());
        List<IdentifityVo> identifity = identifityMapper.getIdentifity(identifityVo, queryWrapper);

        System.out.println(identifity+"查到的数据");
        return identifity;
    }
    //修改鉴定信息
    @Override
    public int updateIdentifity(Identifity identifity) {
        System.out.println(identifity+"业务层"+identifity.getId());
        QueryWrapper<Identifity> updateWrapper = new QueryWrapper<>();
        updateWrapper.eq("id",identifity.getId());
        int update = goIndentifyMapper.update(identifity, updateWrapper);

        return update;
    }


}
