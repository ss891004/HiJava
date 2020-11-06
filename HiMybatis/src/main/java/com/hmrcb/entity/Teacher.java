package com.hmrcb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    //定义实体类的属性，与teacher表中的字段对应
    private int id;            //id===>t_id
    private String name;    //name===>t_name

}


