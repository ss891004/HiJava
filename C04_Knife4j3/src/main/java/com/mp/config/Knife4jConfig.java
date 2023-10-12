package com.mp.config;

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
                .apis(RequestHandlerSelectors.basePackage("com.hm.web"))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("非遗项目 API 文档")
                .description("本文档描述了非遗各个微服务接口定义")
                .version("1.0")
                .contact(new Contact("yanghui", "http://yanghui.com",
                        "yanghui@qq.com"))
                .build();
    }
}
