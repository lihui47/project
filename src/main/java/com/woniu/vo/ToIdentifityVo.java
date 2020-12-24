package com.woniu.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class ToIdentifityVo  {

    //当前页码
    private Integer current;
    //当前页码条数
    private Integer sizePage;
    private String name;
    private String sname;
    private String identitier;
    private String status;



}
