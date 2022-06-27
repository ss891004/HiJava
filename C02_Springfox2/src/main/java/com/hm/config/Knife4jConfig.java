package com.hm.config;

import java.util.ArrayList;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;


@Configuration
@EnableSwagger2WebMvc
//@EnableKnife4j
public class Knife4jConfig {

    /*引入Knife4j提供的扩展类*/
    @Autowired
    private OpenApiExtensionResolver openApiExtensionResolver;

    @Value("${knife4j.enable}")
    private Boolean enabled;

    @Bean
    public Docket docket() {


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("dxiaodang's group")  // 配置api文档的分组
                .enable(enabled)  // 配置是否开启swagger
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hm")) //配置扫描路径
                .paths(PathSelectors.any()) // 配置过滤哪些
                .build()
                // 为了区分开发者在构建自定义文档时，在不同的Docket逻辑分组下进行区别显示
                .extensions(openApiExtensionResolver.buildExtensions("md"));
    }

    // api基本信息
    private ApiInfo apiInfo() {
        return new ApiInfo("ss swagger",
                "测试knife4j-ui",
                "v1.0",
                "http://mail.qq.com",
                new Contact("dangbo", "http://mail.qq.com", "145xxxxx@qq.com"),  //作者信息
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}

