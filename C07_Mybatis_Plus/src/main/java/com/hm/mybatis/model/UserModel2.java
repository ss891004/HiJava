package com.hm.mybatis.model;
import lombok.*;

/*
* lombok可以帮助我们自动生成上面4个字段的get方法、set方法、无参构造方法、有参有参构造方法、
* builder模式构建对象的代码、重写toString方法，这些都在代码编译为字节码之前会写进去，
* */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserModel2 {
    private Long id;
    private String name;
    private Integer age;
    private Integer sex;
    private Double salary;
}

/*
DROP TABLE IF EXISTS `t_user2`;
CREATE TABLE t_user2 (
  id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键，用户id，自动增长',
  `name` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '姓名',
  `age` SMALLINT NOT NULL DEFAULT 1 COMMENT '年龄',
  `salary` DECIMAL(12,2) NOT NULL DEFAULT 0 COMMENT '薪水',
  `sex` TINYINT NOT NULL DEFAULT 0 COMMENT '性别,0:未知,1:男,2:女'
) COMMENT '用户表';

INSERT INTO t_user (`name`,`age`,`salary`,`sex`) VALUES ('路人甲Java',30,50000,1), ('javacode2018',30,50000,1), ('张学友',56,500000,1), ('林志玲',45,88888.88,2);



*
* */