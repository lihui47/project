package com.woniu.domin;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

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
    @TableName("t_user_roler")
@ApiModel(value="UserRole对象", description="")
public class UserRoler implements Serializable {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "用户角色表id")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty(value = "用户表id")
      private Integer uid;

      @ApiModelProperty(value = "角色表id")
      private Integer rid;
      @ApiModelProperty(value = "创建时间")
      @TableField(fill = FieldFill.INSERT)
      private Date gmtCreate;

     @ApiModelProperty(value = "更新时间")
     @TableField(fill = FieldFill.INSERT)
     private Date gmtModifified;

}
