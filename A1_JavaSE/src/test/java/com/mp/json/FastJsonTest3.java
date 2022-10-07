package com.mp.json;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Date;

/**
 * JSONFeild注解：
 *      该注解作用于方法上，字段和参数上，可在序列化和反序列时进行特定功能定制
 */
public class FastJsonTest3 {

    /**
     * 序列化的时候可以做到修改属性名
     */
    @Test
    public void testObjectToJson(){
        Student3 Student3 = new Student3();
        Student3.setId(1);
        Student3.setName("list");
        Student3.setAge(11);
        Student3.setBirthday(new Date());
        Student3.setFlag(true);
        String jsongString = JSON.toJSONString(Student3);
        System.out.println(jsongString);
        /**
         *     //注解属性
         *     @JSONField(name = "StudentName")
         *     private String name;
         *
         *     {"age":11,"bitrthday":1620142567227,"flag":true,"id":1,"StudentName":"list"}
         */

        /**
         *     可以改变出现的位置
         *     @JSONField(name = "StudentName",ordinal = 1)
         *     private String name;
         *     @JSONField(ordinal = 2)
         *     private Integer age;
         *     注解的顺序可以进行调整
         *     {"birthday":1620142775049,"flag":true,"id":1,"StudentName":"list","age":11}
         */


        /**
         *     @JSONField(format = "yyyy-MM-dd")
         *     private Date birthday;
         *     {"birthday":"2021-05-04","flag":true,"id":1,"StudentName":"list","age":11}
         */


        /**
         *     @JSONField(ordinal = 2,serialize = false)指定false就不会进行序列化
         *     private Integer age;
         *    {"birthday":"2021-05-04","flag":true,"id":1,"StudentName":"list"}
         */

    }
}
