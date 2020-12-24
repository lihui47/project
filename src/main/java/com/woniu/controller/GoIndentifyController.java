package com.woniu.controller;

import com.woniu.domin.Identifity;
import com.woniu.domin.ProductAttribute;
import com.woniu.dto.Result;
import com.woniu.dto.StatusCode;
import com.woniu.service.GoIndentifyService;
import com.woniu.service.IdentifityService;
import com.woniu.service.ProductAttributeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/go")
public class GoIndentifyController {
    @Resource
    private GoIndentifyService goIndentifyService;
    @Resource
    private ProductAttributeService productAttributeService;
    @PostMapping("addIndentify")
    public Result addIndentify(@RequestBody Identifity identifity){
        System.out.println(identifity);
        int row=goIndentifyService.insertToUPdateIdentify(identifity);
        if(row>0){
            //鉴定成功后，修改商品表里的状态
            ProductAttribute productAttribute = new ProductAttribute();
            productAttribute.setId(identifity.getPid());
            //productAttribute.setStatus("");
            int result=productAttributeService.updateProductStatus(productAttribute);
            return new Result(true, StatusCode.OK,"鉴定成功");
        }
        return new Result(true, StatusCode.ERROR,"鉴定失败");
    }
}
