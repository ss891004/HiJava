package com.hm.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hm.json.Student2;
import org.junit.Test;
import java.util.Date;

public class FastJsonTest2 {

    /**
     * 序列化为null值的字段 WriteMapNullValue
     */
    @Test
    public void testWriteMapNullValue(){
        Student2 stu = new Student2();
        stu.setId(1);
        stu.setName("zhangsan");
        stu.setAge(20);
        String s1 = JSON.toJSONString(stu);
        System.out.println(s1); //空值是没有被序列化的
        //{"age":20,"id":1,"name":"zhangsan"}

        //SerializerFeature.WriteMapNullValue
        String s2 = JSON.toJSONString(stu, SerializerFeature.WriteMapNullValue);
        // {"age":20,"birthday":null,"flag":null,"id":1,"name":"zhangsan"}
        System.out.println(s2);
    }


    /**
     * 序列化为null的字段，序列化为""
     */
    @Test
    public void testWriteNullStringEnpty(){
        Student2 stu = new Student2();
        stu.setId(1);
        stu.setAge(20);
        String s = JSON.toJSONString(stu,SerializerFeature.WriteNullStringAsEmpty);
        System.out.println(s);
        // {"age":20,"birthday":null,"id":1,"name":""}
    }


    /**
     * 序列化为null的number为0
     */
    @Test
    public void testWriteNullNumberAsZero(){
        Student2 stu = new Student2();
        stu.setId(1);
        stu.setName("zhangsan");
        stu.setBirthday(new Date());
        String s = JSON.toJSONString(stu,SerializerFeature.WriteNullNumberAsZero);
        System.out.println(s);
        // {"age":0,"birthday":1633533979408,"id":1,"name":"zhangsan"}
    }

    /**
     *  WriteNullBooleanAsFalse枚举常量,boolean值为null,序列化为false
     */
    @Test
    public void testWriteNullBooleanAsFalse(){
        Student2 stu = new Student2();
        stu.setId(1);
        stu.setName("zhangsan");
        stu.setAge(20);
        stu.setBirthday(new Date());
        stu.setFlag(true);
        String s = JSON.toJSONString(stu,SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullNumberAsZero);
        System.out.println(s);
    }

    /**
     *  序列化日期格式化
     *
     */
    @Test
    public void testWriteDateUseDateFormatPrettyFormat(){
        Student2 stu = new Student2();
        stu.setId(1);
        stu.setName("zhangsan");
        stu.setAge(20);
        stu.setBirthday(new Date());
        stu.setFlag(true);
        String s = JSON.toJSONString(stu,SerializerFeature.WriteDateUseDateFormat);
        System.out.println(s);
        //{"age":20,"bitrthday":"2021-05-04 23:25:57","flag":true,"id":1,"name":"zhangsan"}

        String s2 = JSON.toJSONString(stu,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.PrettyFormat);
        System.out.println(s2);
    }
}
