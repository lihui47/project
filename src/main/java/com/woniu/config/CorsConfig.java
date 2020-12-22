package com.woniu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author Alex
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //所有路径
        registry.addMapping("/**")
        //授权的源，冲突时使用该配置
        .allowedOrigins("*")
       //.allowedOriginPatterns("*") //springboot 高版本使用 授权的源
        //请求方式
        .allowedMethods("GET","POST","PUT","DELETE","HEAD","OPTIONS")
        //是否开启ajax相关的cookie提交
        .allowCredentials(true)
        //控制哪些header能发送请求
        .allowedHeaders("*")
        //响应资源能够在接下来的 60 分钟以内被复用，而不需要从服务端重新获取
        .maxAge(3600);
    }
}

