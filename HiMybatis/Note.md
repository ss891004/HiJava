## 介绍
+ MyBatis是一个支持普通SQL查询，存储过程和高级映射的优秀持久层框架。MyBatis消除了几乎所有的JDBC代码和参数的手工设置以及对结果集的检索封装。MyBatis可以使用简单的XML或注解用于配置和原始映射，将接口和Java的POJO（Plain Old Java Objects，普通的Java对象）映射成数据库中的记录。
  

## 步骤
+ 1.导入核心依赖
    + mybatis包
    + 数据库驱动包
+ 2.创建mybatis配置文件
+ 3.创建实体类，与数据库表相对应
+ 4.创建mapper 映射文件
+ 5.在配置文件中注册mapper文件


## ORM
+ 数据表映射类
+ 数据表的行映射对象(即实例)
+ 数据表的列映射对象的属性

+ POJO (plain old java objects)


## 基本用法
+ 基于xml的实现
+ 基于注解的实现

### 重要对象
+ SqlSessionFactory
+ SqlSession

### 几个优化配置
+ 一、连接数据库的配置单独放在一个properties文件中
+ 二、为实体类定义别名，简化sql映射xml文件中的引用
```xml
    <!-- 实体类别名 -->
    <typeAliases>
        <!--<typeAlias type="com.qf.entity.User" alias="user_shine"/>-->
        <!-- 定义实体类所在的package，每个实体类都会自动注册一个别名=类名 -->
        <package name="com.hmrcb.entity"/>
    </typeAliases>
```

### 解决字段名与实体类属性名不相同的冲突
+ 冲数据库角度
+ 实体对象角度
+ 参考OrderMapper.xml

