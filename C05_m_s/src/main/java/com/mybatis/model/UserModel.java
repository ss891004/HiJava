package com.mybatis.model;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserModel implements Serializable {
    private Long id;
    private String name;
    private Integer age;
    private Double salary;
}

/*
DROP TABLE IF EXISTS `t_user1`;
CREATE TABLE t_user1 (
id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键，用户id，自动增长',
`name` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '姓名',
`age` SMALLINT NOT NULL DEFAULT 1 COMMENT '年龄',
`salary` DECIMAL(12,2) NOT NULL DEFAULT 0 COMMENT '薪水'
) COMMENT '用户表';
 */