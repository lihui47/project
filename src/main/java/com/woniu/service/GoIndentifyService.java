package com.woniu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.domin.Identifity;
import org.springframework.stereotype.Service;


public interface GoIndentifyService extends IService<Identifity> {
    int insertIdentify(Identifity identifity);
}
