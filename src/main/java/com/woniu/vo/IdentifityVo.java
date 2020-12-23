package com.woniu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdentifityVo  {

    //i.id,p.`name`,s.`name` AS sneme,i.writer,i.identitier,i.`status`,i.gmt_create
    private Integer id;
    private String name;
    private String sname;
    private String writer;
    private String identitier;
    private String status;
    private Date gmt_create;
    //当前页码
    private Integer current;
    //当前页码条数
    private Integer sizePage;
}
