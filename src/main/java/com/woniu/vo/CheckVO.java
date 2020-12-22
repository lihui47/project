package com.woniu.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @ClassName checkVO
 * @Description TODO
 * @Authro SheepSun
 * @Date 2020/12/3 20:18
 * @Version 1.0
 **/

@Component
@Data
public class CheckVO extends PageVO {
    private String id;
    private String name;
    //当前页码
    private Integer current;
    //当前页码条数
    private Integer sizePage;

}
