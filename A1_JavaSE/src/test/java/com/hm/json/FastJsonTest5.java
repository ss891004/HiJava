package com.hm.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONType;
import lombok.Data;
import org.junit.Test;

@Data
//includes指的是要被序列化的字段
//orders明确序列化后的顺序
@JSONType(includes = {"id","name","age","address"},orders = {"name","address","age","id"})
class Person5 {
    private Integer id;
    private String name;
    private Integer age;
    private String address;
}

public class FastJsonTest5 {

    @Test
    public void test(){
        Person5 person = new Person5();
        person.setId(1);
        person.setName("aa");
        person.setAge(11);
        person.setAddress("浙江杭州");
        String s = JSON.toJSONString(person);
        System.out.println(s);
        //{"name":"aa","address":"浙江杭州","age":11,"id":1}
    }
}
