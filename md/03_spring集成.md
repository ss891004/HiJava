## spring的集合项目

### spring集成mybaits (SM)
```text
1、让spring管理SqlSessionFactory
2、让spring管理mapper对象和dao。
   使用spring和mybatis整合开发mapper代理及原始dao接口。
   自动开启事务，自动关闭 sqlsession.
3、让spring管理数据源( 数据库连接池)

```

#### 对一张用户表进行CRUD

### spring集成quartz
+ Spring-quartz.s01_xml
    + 调度器
    + 触发器
    + 任务
+ quartz.properties
  + 配置
+ 一定要导入 spring-context-support 
+ 一个触发器只能给一个job
+ 一个job 可以有多个触发器

### spring集成redis
+ https://blog.csdn.net/feiyangtianyao/article/details/87619128

### mongodb
+ https://blog.csdn.net/qq_16313365/article/details/70142729

