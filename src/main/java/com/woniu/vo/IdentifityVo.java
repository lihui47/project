package com.woniu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Identifity视图对象", description = "")
public class IdentifityVo  {

    @ApiModelProperty(value = "鉴定id")
    private Integer id;
    @ApiModelProperty(value = "鉴定名称")
    private String name;
    @ApiModelProperty(value = "二级类名")
    private String sname;
    @ApiModelProperty(value = "录入人")
    private String writer;
    @ApiModelProperty(value = "鉴定人")
    private String identitier;
    @ApiModelProperty(value = "鉴定状态")
    private String status;
    @ApiModelProperty(value = "创建时间")
    private Date gmt_create;
    @ApiModelProperty(value = "商品价格")
    //商品价格
    private Double price;
    @ApiModelProperty(value = "当前页码")
    //当前页码
    private Integer current;
    //当前页码条数
    @ApiModelProperty(value = "展示条数")
    private Integer sizePage;
    //鉴定价格
    @ApiModelProperty(value = "鉴定价格")
    private Double iprice;
    //新旧程度
    @ApiModelProperty(value = "新旧程度")
    private String oldnew;
    //鉴定结果
    @ApiModelProperty(value = "鉴定结果")
    private String indentifyresult;
}
