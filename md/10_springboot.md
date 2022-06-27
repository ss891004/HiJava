
## 如何创建一个springboot项目
+ jar包
```text
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.5.6</version>
    </parent>
```
+ 具体的starter 包
```text
  <dependencies>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-quartz</artifactId>
    </dependency>
  </dependencies>
```

+ 函数入口
```text
@SpringBootApplication
public class com.hm.Application {
    public static void main(String[] args) {
        SpringApplication.run(com.hm.Application.class, args);
    }
}
```

## 全局配置文件
  + application.properties
  + application.yml

## starter pom
  + 官方
  + 第三方

## 获取自定义配置的值
  + @Value

## 类型安全的配置 (多个配置，有相同的前缀)
  + @ConfigurationProperties(prefix = "book")


## Profile配置 

## 自动配置

## 创建父项目和子项目
+ 使用父级依赖
```text
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>2.5.6</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
```

```text
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.5.6</version>
    </parent>
```

## springBoot的集合项目

### springBoot集成mybaits (单数据源，多数据源)


### JPA
+ Java Persistence API
```text
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
```
+ 创建model 类

|  表头   | 表头  |
|  ----  | ----  |
| @Entity | 表名这是一个Entity类  | 
| @Table(name="数据库中对应的表名") ||
| @Id  |主键给@Id 表名这是一个主键|
|@GeneratedValue|@GeneratedValue(stratefy=GenerationType.IDENTITY)设置自增策略为自动|
|@Column||

+ 创建Repository类
  + JpaRepository<对应的类， 类中主键的类型>

### springBoot集成quartz


## Springboot 引入包
```text
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.5.6</version>
    </parent>
```


### springboot 整合redis （单机，集群）





### 使用 JdbcTemplate
+ 若是当个数据源，springboot 会自动配置
    + 实体类
    + mapper类
    + service类
    + controller类
    + 连接属性配置


### 整合mail
```text
<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-mail -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
    <version>2.5.6</version>
</dependency>

```
+ 发送一封简单邮件
+ 发送一封带附件的邮件
+ 发送一封使用模板的邮件



### 打包
+ war
+ jar


### FastJson