package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.domin.First;
import com.woniu.domin.Second;
import com.woniu.dto.Result;
import com.woniu.dto.StatusCode;
import com.woniu.mapper.FirstMapper;
import com.woniu.mapper.FirstVOMapper;
import com.woniu.mapper.SecondMapper;
import com.woniu.service.SecondService;
import com.woniu.vo.CheckVO;
import com.woniu.vo.FirstVO;
import com.woniu.vo.PageVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sun
 * @since 2020-12-22
 */
@Service("secondService")
public class SecondServiceImpl extends ServiceImpl<SecondMapper, Second> implements SecondService {
    @Resource
    private FirstMapper firstMapper;
    @Resource
    private SecondMapper secondMapper;
    @Resource
    private FirstVOMapper firstVOMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Page querySecondAllInfo(PageVO pageVO) throws Exception {
        Page<FirstVO> page = new Page<>(pageVO.getCurrent(),pageVO.getSizePage());
        //获取分页查询出的封装的数
        page.setRecords(secondMapper.querySecondAndFirstInfo(page,null));

        return page;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Page queryLikeInfo(CheckVO checkVO) throws Exception {
        QueryWrapper<FirstVO> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("s1.`name`    ",checkVO.getName());
        Page<FirstVO> firstVOPage = new Page<>(checkVO.getCurrent(),checkVO.getSizePage());
        firstVOMapper.querySecondAndFirstInfo(firstVOPage,queryWrapper);
        return firstVOPage;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Result insertSecondInfo(Second second) throws Exception {
        boolean b =true;
        QueryWrapper<First> wrapper = new QueryWrapper<>();
        wrapper.eq("name",second.getName());
        First first = firstMapper.selectOne(wrapper);
        if(!ObjectUtils.isEmpty(second)){
            if(first.getId()!=null){
                second.setName(second.getSecondName());
                second.setFid(first.getId());
                List<Second> seconds = secondMapper.selectList(null);
                for(Second s:seconds){
                    if (s.getName().equals(second.getName())){
                        b=false;
                    }
                }
                if (b){
                    secondMapper.insert(second);
                    return new Result(true, StatusCode.OK,"新增成功");
                }
            }
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Result updataSecoondInfo(Second second) throws Exception {
        boolean b =true;
        QueryWrapper<First> wrapper = new QueryWrapper<>();
        wrapper.eq("name",second.getName());
        First first = firstMapper.selectOne(wrapper);
        if(!ObjectUtils.isEmpty(second)){
            if(first.getId()!=null){
                second.setName(second.getSecondName());
                second.setFid(first.getId());
                List<Second> seconds = secondMapper.selectList(null);
                for(Second s:seconds){
                    if (s.getName().equals(second.getName())){
                        b=false;
                    }
                }
                if (b){
                    QueryWrapper<Second> w = new QueryWrapper<>();
                    w.eq("id",second.getId());
                    secondMapper.update(second,w);
                    return new Result(true, StatusCode.OK,"修改成功");
                }
            }
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Result deleteSecondInfo(Second second) throws Exception {
        if(!ObjectUtils.isEmpty(second)){
            QueryWrapper<Second> wrapper = new QueryWrapper<>();
            wrapper.eq("id",second.getId());
            secondMapper.delete(wrapper);
            return new Result(true,StatusCode.OK,"删除成功");
        }
        return null;
    }

    //查询一级大类名称
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Result querySecondAndFirst() throws Exception {
        List<First> firsts = firstMapper.selectList(null);
        return new Result(true,StatusCode.OK,"查询成功",firsts);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Result querySecondInfoByFirstId(CheckVO checkVO) throws Exception {
        QueryWrapper<FirstVO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("f1.id",checkVO.getId());
        Page<FirstVO> firstVOPage = new Page<>(checkVO.getCurrent(),checkVO.getSizePage());
        firstVOMapper.querySecondAndFirstInfo(firstVOPage,queryWrapper);
        return new Result(true,StatusCode.OK,"查询成功",firstVOPage);
    }
}
