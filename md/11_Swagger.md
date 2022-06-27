## 几个概念
+ OpenApi 是业界真正的 api 文档标准 (OAS 即 openAPI描述规范)
+ Swagger 是一个 api 文档维护组织，后来成为了 Open API 标准的主要定义者，现在最新的版本为17年发布的 Swagger3（Open Api3）。
+ SpringFox 是 spring 社区维护的一个项目（非官方），帮助使用者将 swagger2 集成到 Spring 中。
+ SpringDoc 是 spring 社区维护的一个项目（非官方），帮助使用者将 swagger3 集成到 Spring 中。

## Swagger2和Swagger3的注解
+ swagger2的包名为 io.swagger，而swagger3的包名为 io.swagger.core.v3。

|swagger2	|OpenAPI 3	|注解位置|
| -----| ---- |---- |
|@Api |	@Tag(name = “接口类描述”)	 |Controller 类上
|@ApiOperation	 |@Operation(summary =“接口方法描述”)	 |Controller 方法上
|@ApiImplicitParams	 |@Parameters |	Controller 方法上
|@ApiImplicitParam |	@Parameter(description=“参数描述”)	 |Controller 方法上 @Parameters 里
|@ApiParam |	@Parameter(description=“参数描述”)	 |Controller 方法的参数上
|@ApiIgnore	 |@Parameter(hidden = true) 或 @Operation(hidden = true) 或 @Hidden	 |-
|@ApiModel	 |@Schema	 |DTO类上
|@ApiModelProperty |	@Schema |	DTO属性上
|@ApiResponses | | Controller 方法的参数上
|@ApiResponse| | Controller 方法的参数上 


## Swagger 2.9.X 版本
+ 配置类的里面的内容和2.10.x 版本一样
```java
@Configuration //说明这是一个配置类
@EnableSwagger2 //该注解开启Swagger2的自动配置
public class SwaggerConfig{}
```
+ 引入的jar
```text
    <dependency>
      <groupId>io.springfox</groupId>
      <artifactId>springfox-swagger2</artifactId>
      <version>2.9.2</version>
    </dependency>

    <dependency>
      <groupId>io.springfox</groupId>
      <artifactId>springfox-swagger-ui</artifactId>
      <version>2.9.2</version>
    </dependency>
```
+ 访问方式
  + http://localhost:port/context-path/swagger-ui.html

## Swagger 2.10.X 版本
```text
1）、2.10开始支持两种模式：WebFlux，WebMVC
注解已经修改，不支持@EnableSwagger2了，修改为以下两种，请选择一种：
@EnableSwagger2WebMvc
@EnableSwagger2WebFlux
（2）maven依赖变化，之前是两个，现在为3个依赖

 [webflux]
```
+ @EnableSwagger2WebFlux
```text
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>${swagger.version}</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-spring-webflux</artifactId>
            <version>${swagger.version}</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>${swagger.version}</version>
        </dependency>
```

+ @EnableSwagger2WebMvc
```text
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.10.5</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.10.5</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-spring-webmvc</artifactId>
            <version>2.10.5</version>
        </dependency>
```
+ 访问方式
  + http://localhost:port/context-path/swagger-ui.html

## Swagger 3.0.x 版本
+ aa


## Knifej
+ 前身是 swagger-bootstrap-ui，版本 从1.9.6 开始
+ 几个版本说明

|版本|说明|
| -----| ---- |
| 1.9.6	|蓝色皮肤风格，增加更多后端模块|
| 2.0~2.0.5	|Ui重写，蓝色背景变成黑色，底层依赖的springfox框架版本是2.9.2|
|2.0.6~	 |底层springfox框架版本升级知2.10.5,OpenAPI规范是v2|
| 3.0~	|底层依赖springfox框架版本升级至3.0.3,OpenAPI规范是v3|


## Knifej 3.0.X 版本
+ 2.0.6版本前，需要在配置文件中手动使用@EnableKnife4j来使用增强。
+ 2.0.6版本后,只需要在配置文件中配置knife4j.enable=true即可不在使用注解
  + 注意：要使用Knife4j提供的增强，knife4j.enable=true必须开启
  + OpenApiExtensionResolver辅助类需要配置knife4j.enable=true才能自动@Autowired

|属性|	默认值|	说明值|
| -----| ---- |---- |
|knife4j.enable	|false|	是否开启Knife4j增强模式
|knife4j.cors	|false	|是否开启一个默认的跨域配置,该功能配合自定义Host使用
|knife4j.production	|false	|是否开启生产环境保护策略,详情参考文档
|knife4j.basic	|	|对Knife4j提供的资源提供BasicHttp校验,保护文档
|knife4j.basic.enable	|false	|关闭BasicHttp功能
|knife4j.basic.username|	|	basic用户名
|knife4j.basic.password	|	|basic密码
|knife4j.documents	| |	自定义文档集合，该属性是数组
|knife4j.documents.group	| |	所属分组
|knife4j.documents.name	| |	类似于接口中的tag,对于自定义文档的分组
|knife4j.documents.locations	| |	markdown文件路径,可以是一个文件夹(classpath:markdowns/*)，也可以是单个文件(classpath:md/sign.md)
|knife4j.setting	| |	前端Ui的个性化配置属性
|knife4j.setting.enableAfterScript|	true|	调试Tab是否显示AfterScript功能,默认开启
|knife4j.setting.language|	zh-CN|	Ui默认显示语言,目前主要有两种:中文(zh-CN)、英文(en-US)
|knife4j.setting.enableSwaggerModels	|true|	是否显示界面中SwaggerModel功能
|knife4j.setting.swaggerModelName|	Swagger Models|	重命名SwaggerModel名称,默认
|knife4j.setting.enableDocumentManage	|true|	是否显示界面中"文档管理"功能
|knife4j.setting.enableReloadCacheParameter	|false	|是否在每个Debug调试栏后显示刷新变量按钮,默认不显示
|knife4j.setting.enableVersion	|false|	是否开启界面中对某接口的版本控制,如果开启，后端变化后Ui界面会存在小蓝点
|knife4j.setting.enableRequestCache|	true|	是否开启请求参数缓存
|knife4j.setting.enableFilterMultipartApis	|false|	针对RequestMapping的接口请求类型,在不指定参数类型的情况下,如果不过滤,默认会显示7个类型的接口地址参数,如果开启此配置,默认展示一个Post类型的接口地址
|knife4j.setting.enableFilterMultipartApiMethodType	|POST|	具体接口的过滤类型
|knife4j.setting.enableHost	|false|	是否启用Host
|knife4j.setting.enableHomeCustom	|false	|是否开启自定义主页内容
|knife4j.setting.homeCustomLocation	|	|主页内容Markdown文件路径
|knife4j.setting.enableSearch	|false|	是否禁用Ui界面中的搜索框
|knife4j.setting.enableFooter	|true|	是否显示Footer
|knife4j.setting.enableFooterCustom	|false|	是否开启自定义Footer
|knife4j.setting.footerCustomContent	|false|	自定义Footer内容
|knife4j.setting.enableDynamicParameter	|false|	是否开启动态参数调试功能
|knife4j.setting.enableDebug	|true|	启用调试
|knife4j.setting.enableOpenApi	|true|	显示OpenAPI规范
|knife4j.setting.enableGroup	|true|	显示服务分组

+ http://{ip}:{port}/doc.html

### Springdoc 版本