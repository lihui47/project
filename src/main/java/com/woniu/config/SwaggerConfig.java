package com.woniu.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2//开启swagger
  public class SwaggerConfig {
    @Bean
    public Docket docket(){
    return new Docket(DocumentationType.SWAGGER_2)
        .pathMapping("/")//接上面的new的匿名对象
        .select()
        //扫描接口的包
        .apis(RequestHandlerSelectors.basePackage("com.woniu.controller"))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(new ApiInfoBuilder()
                 //标题
                 .title("springboot整合swagger")
                 //描述
                 .description("springboot整合swagger具体请看详细信息")
                 //版本
                 .version("1.0")
                 //联系人信息
                 .contact(new Contact("老孙","https://sxj1991.github.io","565260908lazzysun@gmail.com"))
                 //项目主页
                 .license("项目主页")
                 .licenseUrl("https://sxj1991.github.io")
                 .build());
    }
  
}