package com.woniu.domin;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-12-23
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @TableName("t_warehouse")
@ApiModel(value="Warehouse对象", description="")
public class Warehouse implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String name;

    private String manager;

    private String tel;

    private String address;

    private String associatemouse;

    private String status;


}
