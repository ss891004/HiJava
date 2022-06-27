package com.hm.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONType;
import lombok.Data;
import org.junit.Test;

@Data
//includes指的是要被序列化的字段
@JSONType(includes = {"id","age","address"})
class Person4 {
        private Integer id;
        private String name;
        private Integer age;
        private String address;
    }


/**
 * JSONType注解：
 *      该注解作用于方法上，字段和参数上，可在序列化和反序列时进行特定功能定制
 */
public class FastJsonTest4 {

    @Test
    public void test() {
        Person4 person = new Person4();
        person.setId(1);
        person.setName("aa");
        person.setAge(11);
        person.setAddress("浙江杭州");
        String s = JSON.toJSONString(person);
        System.out.println(s);
        //{"address":"浙江杭州","age":11,"id":1} name没有被序列化
    }
}
