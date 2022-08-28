package com.hm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User1Model {
    private Long id;
    private String name;
    private String mail;
    private int age;
}
/*

DROP TABLE IF EXISTS `t_user1`;
        CREATE TABLE t_user1 (
        id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键，用户id，自动增长',
        `name` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '姓名',
                `mail` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '邮箱',
                        `age` int NOT NULL DEFAULT 1 COMMENT '姓名'
        ) COMMENT '用户表';
*/