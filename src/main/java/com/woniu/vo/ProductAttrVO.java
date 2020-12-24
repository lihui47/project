package com.woniu.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @ClassName ProductAttrVO
 * @Description TODO
 * @Authro SheepSun
 * @Date 2020/12/23 19:06
 * @Version 1.0
 **/
@Component
@Data
public class ProductAttrVO implements Serializable {
    private Integer id;

    private String name;

    private String secondName;

    private Integer number;

    private double price;

    private String status;

    @TableField(exist = false)
    private String statusName;
    @TableField(exist = false)
    private String userName;
}
