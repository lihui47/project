package com.woniu.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @ClassName PageVO
 * @Description 分页查询数据类
 * @Authro SheepSun
 * @Date 2020/12/22 11:18
 * @Version 1.0
 **/
@Component
@Data
public class PageVO  {
    //当前页码
    private Integer current;
    //当前页码条数
    private Integer sizePage;
}
