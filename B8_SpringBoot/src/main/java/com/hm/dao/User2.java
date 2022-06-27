package com.hm.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


//使用JPA注解进行配置映射关系
@Entity //说明它是和数据表映射的类
@Table(name = "t_user2") //指定对应映射的表名，省略默认表名就是类名
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User2 {
    @Id //标识主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //标识自增长主键
    private Long id;

    @Column
    private String mail;
    @Column(name = "name",length = 5) //这是和数据表对应的一个列
    private String uName;

    @Column
    private Integer age;
}