package com.woniu.vo;

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
public class IdentifityVo  {


    private Integer id;
    private String name;
    private String sname;
    private String writer;
    private String identitier;
    private String status;
    private Date gmt_create;
    //商品价格
    private Double price;
    //当前页码
    private Integer current;
    //当前页码条数
    private Integer sizePage;
    //鉴定价格
    private Double iprice;
    //新旧程度
    private String oldnew;
    //鉴定结果
    private String indentifyresult;
}
