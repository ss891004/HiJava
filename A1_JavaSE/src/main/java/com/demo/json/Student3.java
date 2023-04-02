package com.demo.json;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * FastJson
 */
@Data
public class Student3 {
    private Integer id;
    //注解属性
    @JSONField(name = "studentName",ordinal = 1)
    private String name;
    @JSONField(ordinal = 2,serialize = false)
    private Integer age;


    @JSONField(format = "yyyy-MM-dd")
    private Date birthday;

    //添加一个布尔值
    private Boolean flag;
}
