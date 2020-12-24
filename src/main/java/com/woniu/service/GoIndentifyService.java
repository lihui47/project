package com.woniu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.domin.Identifity;
import com.woniu.domin.ProductAttribute;
import org.springframework.stereotype.Service;



public interface GoIndentifyService extends IService<Identifity> {
    int insertToUPdateIdentify(Identifity identifity);
    //根据商品id查询商品
    ProductAttribute selectProductById(Integer pid);
    //根据鉴定id查找鉴定
    Identifity selectIdentifyById(Integer id);
}
