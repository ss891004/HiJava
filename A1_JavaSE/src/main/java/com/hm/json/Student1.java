package com.hm.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student1 {
    private Integer id;
    private String name;
    private Integer age;
    private Date birthday;
}

