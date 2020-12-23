package com.woniu.domin;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author team
 * @since 2020-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_identifity")
@ApiModel(value="Identifity对象", description="")
public class Identifity implements Serializable {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "鉴定id")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty(value = "商品名称")
      private Integer pid;

      @ApiModelProperty(value = "录入人")
      private String writer;

      @ApiModelProperty(value = "鉴定人")
      private String identitier;

      @ApiModelProperty(value = "状态(默认未鉴定 状态:0)")
      private String status;

      @ApiModelProperty(value = "创建时间")
        @TableField(fill = FieldFill.INSERT)
      private Date gmtCreate;

      @ApiModelProperty(value = "更新时间")
      private Date gmtModifified;

    private BigDecimal identifyPrice;


}
