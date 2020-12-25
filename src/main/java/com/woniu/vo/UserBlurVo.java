package com.woniu.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserBlurVo {
    @ApiModelProperty(value = "输入框查询内容")
    private String input3;
    @ApiModelProperty(value = "输入框查询条件")
    private String select;
}
