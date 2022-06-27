package com.hm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class Knife4jConfig {
    @Bean
    public Docket webApiConfig(){

        return new Docket(DocumentationType.OAS_30)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hm.controller"))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("API 文档")
                .description("服务接口定义")
                .version("1.0")
                .contact(new Contact("ss", "http://www.baidu.com",
                        "575726653@qq.com"))
                .build();
    }
}
