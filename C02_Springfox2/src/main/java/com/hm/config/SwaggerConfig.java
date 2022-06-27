package com.hm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@EnableSwagger2WebMvc
@Configuration
public class SwaggerConfig {
    @Value("${swagger.enabled}")
    private Boolean enabled;

    @Bean
    @SuppressWarnings("all")
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                //apiInfo： 添加api详情信息，参数为ApiInfo类型的参数，这个参数包含了第二部分的所有信息比如标题、描述、版本之类的，开发中一般都会自定义这些信息
                .apiInfo(apiInfo())
                //.groupName("hrcb")
                //配置是否启用Swagger，如果是false，在浏览器将无法访问，默认是true
                .enable(true)
                .select()
                //apis： 添加过滤条件,
                .apis(RequestHandlerSelectors.basePackage("com.hm"))
                //paths： 这里是控制哪些路径的api会被显示出来，比如下方的参数就是除了/user以外的其它路径都会生成api文档
                //.paths((String a) -> !a.equals("/user"))
                .build();
    }


    //配置多个分组
    @Bean
    public Docket createRestApi2(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("组一")
                .select()
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("使用Swagger2构建RESTful APIs") //标题内容
                .description("描述内容")
                //.termsOfServiceUrl("http://www.baidu.com")
                .version("2.0")
                //.license("许可")
                //.licenseUrl("许可链接")
                //.contact(new Contact("yyyy", "zzzz", "aaaa"))
                .build();
    }
















}
