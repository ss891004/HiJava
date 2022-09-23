package com.mybatis.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserModel3 {
    private Long id;
    private String name;
}

/*
DROP TABLE IF EXISTS t_user3;
 CREATE TABLE t_user3(
 id int AUTO_INCREMENT PRIMARY KEY COMMENT '用户id',
 name VARCHAR(32) NOT NULL DEFAULT '' COMMENT '用户名'
 ) COMMENT '用户表';
 INSERT INTO t_user3 VALUES (1,'张学友'),(2,'路人甲Java');
 */
