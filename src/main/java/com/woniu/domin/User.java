package com.woniu.domin;

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
 * @since 2020-12-21
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @TableName("t_user")
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "用户id")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty(value = "用户名")
      private String username;

      @ApiModelProperty(value = "用户密码")
      private String password;

      @ApiModelProperty(value = "创建时间")
        @TableField(fill = FieldFill.INSERT)
      private Date gmtCreate;

      @ApiModelProperty(value = "更新时间")
        @TableField(fill = FieldFill.INSERT_UPDATE)
      private Date gmtModifified;

      private String salt;

      private String status;


}
