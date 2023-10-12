package com.mp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("tbl_employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;  // 若使用雪花算法的id，必须是long 类型的
    @TableField(value = "last_name")
    private String lastName;
    @TableField(value = "email")
    private String email;
    @TableField(value = "gender")
    private Integer gender;
    @TableField(value = "age")
    private Integer age;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }
}

  /*
    ## 创建库
CREATE DATABASE mp;
## 使用库
USE mp;
## 创建表
CREATE TABLE tbl_employee(
   id INT(11) PRIMARY KEY AUTO_INCREMENT,
   last_name VARCHAR(50),
   email VARCHAR(50),
   gender CHAR(1),
   age INT
);
## 导入数据
INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Tom','tom@qq.com',1,22);
INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Jerry','jerry@qq.com',0,25);
INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Black','black@qq.com',1,30);
INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('White','white@qq.com',0,35);
INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Tiger','tiger@qq.com',1,28);
INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Bobby','bobby@qq.com',0,16);
## 查询数据
SELECT * FROM tbl_employee;
*/
//
//    CREATE TABLE tbl_employee(
//       id bigint PRIMARY KEY  ,
//    last_name VARCHAR(50),
//    email VARCHAR(50),
//    gender CHAR(1),
//    age INT
//);