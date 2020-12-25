package com.woniu.controller;

import com.woniu.domin.Identifity;
import com.woniu.domin.ProductAttribute;
import com.woniu.dto.Result;
import com.woniu.dto.StatusCode;
import com.woniu.service.GoIndentifyService;
import com.woniu.service.IdentifityService;
import com.woniu.service.ProductAttributeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/go")
@Api(tags = {"商品鉴定的接口controller"})
public class GoIndentifyController {
    @Resource
    private GoIndentifyService goIndentifyService;
    @Resource
    private ProductAttributeService productAttributeService;

    @GetMapping("addIndentify")
    @ApiOperation(value = "鉴定商品功能")
    public Result addIndentify( Identifity identifity){
        System.out.println(identifity);
        int row=goIndentifyService.insertToUPdateIdentify(identifity);
        if(row>0){
            //鉴定成功后，根据鉴定id查询鉴定表
            Identifity ii=goIndentifyService.selectIdentifyById(identifity.getId());
            ProductAttribute productAttribute=goIndentifyService.selectProductById(ii.getPid());
            //ProductAttribute productAttribute = new ProductAttribute();
            System.out.println(productAttribute+"123456789");
            productAttribute.setId(ii.getPid());
            productAttribute.setStatus("已鉴定");
            //鉴定成功后，修改商品表里的状态
            int result=productAttributeService.updateProductStatus(productAttribute);
            if(result<0){
                return new Result(true, StatusCode.OK,"修改失败");
            }
            return new Result(true, StatusCode.OK,"鉴定成功");
        }
        return new Result(true, StatusCode.ERROR,"鉴定失败");
    }
}
