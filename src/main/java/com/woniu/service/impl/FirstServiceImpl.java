package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.woniu.domin.First;
import com.woniu.dto.Result;
import com.woniu.dto.StatusCode;
import com.woniu.mapper.FirstMapper;
import com.woniu.service.FirstService;
import com.woniu.vo.CheckVO;
import com.woniu.vo.PageVO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
@Service("firstService")
public class FirstServiceImpl extends ServiceImpl<FirstMapper, First> implements FirstService {

    @Resource
    private FirstMapper firstMapper;
    @Override
    public Page queryFirstAllInfo(PageVO pageVO) throws Exception {
        //创建分页查询对象
        Page<First> page =new Page(pageVO.getCurrent(),pageVO.getSizePage());
        //获取分页查询出的封装的数据
        firstMapper.selectPage(page, null);
        return page;
    }

    @Override
    public Page queryLikeInfo(CheckVO checkVO) throws Exception {
        //创建分页查询对象
        Page<First> page =new Page(checkVO.getCurrent(),checkVO.getSizePage());
        //获取分页查询出的封装的数据
        QueryWrapper<First> wrapper = new QueryWrapper<>();
        if(StringUtils.hasLength(checkVO.getName())){
            wrapper.like("name",checkVO.getName());
            firstMapper.selectPage(page, wrapper);
            return page;
        }
        return null;
    }

    @Override
    public Result insertFirstInfo(First first) throws Exception {
        boolean b=true;
        //查询数据库
        List<First> firsts = firstMapper.selectList(null);
        for(First f:firsts){
            //如果数据库存在相同数据则不新增
            if(f.getName().equals(first.getName())){
                b=false;
            }
        }
        if(b){
            firstMapper.insert(first);
            return new Result(true, StatusCode.OK,"新增成功");
        }
        return new Result(true, StatusCode.ERROR,"新增失败");
    }

    @Override
    public Result updataFirstInfo(First first) throws Exception {
        boolean b=true;
        //查询数据库
        List<First> firsts = firstMapper.selectList(null);
        for(First f:firsts){
            //如果数据库存在相同数据则不修改
            if(f.getName().equals(first.getName())){
                b=false;
            }
        }
        if(b&&StringUtils.hasLength(first.getName())){
            QueryWrapper<First> wrapper = new QueryWrapper<>();
            wrapper.eq("id",first.getId());
            firstMapper.update(first, wrapper);
            return new Result(true, StatusCode.OK,"修改成功");
        }
        return new Result(true, StatusCode.ERROR,"修改失败");


    }

    @Override
    public Result deleteFirstInfo(First first) throws Exception {
        QueryWrapper<First> firstQueryWrapper = new QueryWrapper<>();
        firstQueryWrapper.eq("id",first.getId());
        firstMapper.delete(firstQueryWrapper);
        return new Result(true,StatusCode.OK,"删除成功");
    }

}
