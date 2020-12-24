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
    @GetMapping("addIndentify")
    public Result addIndentify( Identifity identifity){
        System.out.println(identifity);
        int row=goIndentifyService.insertToUPdateIdentify(identifity);
        if(row>0){
            //鉴定成功后，根据鉴定id查询鉴定表
            System.out.println(row+"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            Identifity ii=goIndentifyService.selectIdentifyById(identifity.getId());
            System.out.println(ii.getPid());
            ProductAttribute productAttribute=goIndentifyService.selectProductById(identifity.getPid());
            //ProductAttribute productAttribute = new ProductAttribute();
            System.out.println(productAttribute+"123456789");
            productAttribute.setId(ii.getPid());
            productAttribute.setStatus("已鉴定");
            //鉴定成功后，修改商品表里的状态
            System.out.println("shhdjashdgasgsgh"+productAttribute);
            int result=productAttributeService.updateProductStatus(productAttribute);
            if(result<0){
                return new Result(true, StatusCode.OK,"修改失败");
            }
            return new Result(true, StatusCode.OK,"鉴定成功");
        }
        return new Result(true, StatusCode.ERROR,"鉴定失败");
    }
}
