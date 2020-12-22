package com.woniu.component;

import com.woniu.dto.Result;
import com.woniu.dto.StatusCode;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author Alex
 */
//@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({UnauthorizedException.class})
    public Result<Object> handlerUnauthorizedException(){ return new Result<>(false, StatusCode.ERROR,"权限不足"); }

    @ExceptionHandler({UnauthorizedException.class})
    public Result<Object> handlerMySQLSyntaxErrorException(){ return new Result<>(false, StatusCode.ERROR,"sql语句异常"); }

    @ExceptionHandler({Exception.class})
    public Result<Object> handlerException(){
        return new Result<>(false, StatusCode.ERROR,"未知异常");
    }
}
