package com.demo.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.List;

public class FastJsonUtil {

    public static void main(String[] args) {

        List<Student1> a = new ArrayList<>();

        System.out.println(JSON.toJSONString(a));

        Student1 s1 = new Student1();
        s1.setAge(10);
        s1.setStudent2List(new ArrayList<>());

        a.add(s1);

        System.out.println(JSON.toJSONString(a, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullListAsEmpty));


    }


}
