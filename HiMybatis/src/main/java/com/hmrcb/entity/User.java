package com.hmrcb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private Boolean gender;
    private Date registTime;
}

/*
create database mybatis default charset =utf8;
create table t_user(
   id int primary key auto_increment,
   username varchar(50),
   password varchar(50),
   gender tinyint,
   regist_time datetime
)default charset =utf8;
* */