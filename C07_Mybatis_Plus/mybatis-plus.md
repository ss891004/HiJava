# 增强 mybatis-plus

## 原始mybatis使用过程
```text
CREATE DATABASE `mybatis_plus` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
use `mybatis_plus`;
CREATE TABLE `user` (
`id` bigint(20) NOT NULL COMMENT '主键ID',
`name` varchar(30) DEFAULT NULL COMMENT '姓名',
`age` int(11) DEFAULT NULL COMMENT '年龄',
`email` varchar(50) DEFAULT NULL COMMENT '邮箱',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_user2` (
`id` bigint(20) NOT NULL COMMENT '主键ID',
`username` varchar(30) DEFAULT NULL COMMENT '姓名',
`age` int(11) DEFAULT NULL COMMENT '年龄',
`email` varchar(50) DEFAULT NULL COMMENT '邮箱',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO user (id, name, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', 24, 'test5@baomidou.com');

INSERT INTO t_user2 (id, username, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', 24, 'test5@baomidou.com');
```

+ Spring 整合 mybatis
  + 创建实体 
  + 创建MyBatis的核心配置文件 
  + 创建mapper接口和映射文件 
    + mapper接口： 
    + mapper映射文件： 
  + 创建jdbc.properties 
  + 创建Spring的配置文件

## mybatis-plus 的 CRUD

+ Spring 整合 mybatis-plus
  + 修改Spring的配置文件
  + 创建mapper接口

+ BaseMapper
+ 通用Service

## 常用注释
+ @TableName
+ @TableId
+ @TableField
+ @TableField

## 条件构造器和常用接口
Wrapper ： 条件构造抽象类，最顶端父类
  AbstractWrapper ： 用于查询条件封装，生成 sql 的 where 条件
    QueryWrapper ： 查询条件封装
    UpdateWrapper ： Update 条件封装
    AbstractLambdaWrapper ： 使用Lambda 语法
      LambdaQueryWrapper ：用于Lambda语法使用的查询Wrapper
      LambdaUpdateWrapper ： Lambda 更新封装Wrapper

+ QueryWrapper