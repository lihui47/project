package com.woniu.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MybatisConfig
 * @Description TODO
 * @Authro SheepSun
 * @Date 2020/12/21 21:31
 * @Version 1.0
 **/
@Configuration
public class MybatisConfig {
    // 分页查询组件
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
