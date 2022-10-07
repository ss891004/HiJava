package com.mp.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student1 {
    private Integer id;
    private String name;
    private Integer age;
    private Date birthday;

    private List<Student2> student2List;
}

