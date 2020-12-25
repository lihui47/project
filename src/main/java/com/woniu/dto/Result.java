package com.woniu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "数据封装传输对象", description = "")
public class Result<T> implements Serializable {
    @ApiModelProperty(value = "状态")
    private boolean flag;//是否成功
    @ApiModelProperty(value = "状态码")
    private Integer code;//响应状态码
    @ApiModelProperty(value = "返回信息")
    private String message;//返回消息
    @ApiModelProperty(value = "返回封装数据")
    private T data;//返回数据

    public Result(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = (T) data;
    }

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result() {
        this.flag = true;
        this.code = StatusCode.OK;
        this.message = "操作成功!";
    }    
}