package com.woniu.domin;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableName("t_permission")
@ApiModel(value="Permission对象", description="")
public class Permission implements Serializable {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "权限id")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty(value = "权限名称")
      @TableField("permissionName")
    private String permissionName;

      @ApiModelProperty(value = "url")
      private String url;

    private Integer level;

    private Integer parentid;


}
