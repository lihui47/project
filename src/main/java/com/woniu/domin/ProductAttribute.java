package com.woniu.domin;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName ProductAttribute
 * @Description TODO
 * @Authro SheepSun
 * @Date 2020/12/23 16:03
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_product")
@ApiModel(value = "ProductAttribute对象", description = "")
@Component
public class ProductAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer sid;

    private Integer number;

    private double price;

    private String status;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModifified;

    @TableField(exist = false)
    private String statusName;

    @TableField(exist = false)
    private String secondName;

    @TableField(exist = false)
    private String userName;
}
