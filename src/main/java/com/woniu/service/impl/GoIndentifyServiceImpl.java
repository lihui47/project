package com.woniu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.domin.Identifity;
import com.woniu.mapper.GoIndentifyMapper;
import com.woniu.mapper.IdentifityMapper;
import com.woniu.service.GoIndentifyService;
import com.woniu.service.IdentifityService;
import com.woniu.vo.IdentifityVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class GoIndentifyServiceImpl extends ServiceImpl<GoIndentifyMapper, Identifity> implements GoIndentifyService {
    @Resource
    private GoIndentifyMapper goIndentifyMapper;
    @Override
    public int insertToUPdateIdentify(Identifity identifity) {
        int insert = goIndentifyMapper.updateById(identifity);
        if(insert>0){
            return 1;
        }
        return 0;
    }
}
