package com.mp.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.junit.Test;

import java.util.*;

public class FastJsonTest1 {

    /**
     * java对象转为json格式的字符串
     */
    @Test
    public void testObjectToJson(){
        Student1 Student1 = new Student1();

        Student1.setId(1);
        Student1.setName("list");
        Student1.setAge(11);
        Student1.setBirthday(new Date());

        //转化
        String jsonString = JSON.toJSONString(Student1);
        //{"age":11,"birthday":1633533114749,"id":1,"name":"list"}
        System.out.println(jsonString);
    }

    /**
     * java中的集合转换为json
     */
    @Test
    public void testListToJson() {
        List<Student1> list = new ArrayList<Student1>();

        Student1 Student11 = new Student1();
        Student11.setId(1);
        Student11.setName("list");
        Student11.setAge(11);
        Student11.setBirthday(new Date());

        Student1 Student12 = new Student1();
        Student12.setId(2);
        Student12.setName("lisa");
        Student12.setAge(12);
        Student12.setBirthday(new Date());

        list.add(Student11);
        list.add(Student12);

        //list集合序列华为json格式的字符串
        String s = JSON.toJSONString(list);
        System.out.println(s);
        //[
        // {"age":11,"birthday":1620138289599,"id":1,"name":"list"},
        // {"age":12,"birthday":1620138289599,"id":2,"name":"lisa"}
        // ]
    }

    /**
     * map集合
     */
    @Test
    public void testMapToJson(){
        Map<String,Student1> map = new HashMap<String, Student1>();
        Student1 Student11 = new Student1();
        Student11.setId(1);
        Student11.setName("list");
        Student11.setAge(11);
        Student11.setBirthday(new Date());

        Student1 Student12 = new Student1();
        Student12.setId(2);
        Student12.setName("lisa");
        Student12.setAge(12);
        Student12.setBirthday(new Date());

        //map集合存储对象
        map.put("stu1",Student11);
        map.put("stu2",Student12);

        String s = JSON.toJSONString(map);
        System.out.println(s);
        // {
        // "stu2":{"age":12,"birthday":1620138549606,"id":2,"name":"lisa"},
        // "stu1":{"age":11,"birthday":1620138549606,"id":1,"name":"list"}
        // }
    }


    /**
     * 反序列化
     */

    /**
     * json 反序列化为 java对象
     */
    @Test
    public void testJsonToObject(){
        String json = "{\"age\":11,\"birthday\":1620137932283,\"id\":1,\"name\":\"list\"}";
        Student1 Student1 = JSON.parseObject(json, Student1.class);
        System.out.println(Student1);
        //Student1(id=1, name=list, age=11, bitrthday=Tue May 04 22:18:52 CST 2021)
    }

    /**
     * json 反序列化为 list集合
     */
    @Test
    public void testJsonToList(){
        String json = "[{\"age\":11,\"birthday\":1620138289599,\"id\":1,\"name\":\"list\"},{\"age\":12,\"birthday\":1620138289599,\"id\":2,\"name\":\"lisa\"}]";
        List<Student1> list = JSON.parseArray(json, Student1.class);
        list.forEach(System.out::println);
        /**
         * Student1(id=1, name=list, age=11, birthday=Tue May 04 22:24:49 CST 2021)
         * Student1(id=2, name=lisa, age=12, birthday=Tue May 04 22:24:49 CST 2021)
         */
    }

    @Test
    public void testJsonToMap(){
        String json = "{\"stu2\":{\"age\":12,\"birthday\":1620138549606,\"id\":2,\"name\":\"lisa\"},\"stu1\":{\"age\":11,\"birthday\":1620138549606,\"id\":1,\"name\":\"list\"}}";

        //传入转后的map集合,最后部分需要添加一个括号{}
        Map<String, Student1> map = JSON.parseObject(json, new TypeReference<Map<String, Student1>>(){});
        for (String key:map.keySet()){
            System.out.println(key+":"+map.get(key));
        }
        /**
         * stu2:Student1(id=2, name=lisa, age=12, birthday=Tue May 04 22:29:09 CST 2021)
         * stu1:Student1(id=1, name=list, age=11, birthday=Tue May 04 22:29:09 CST 2021)
         */
    }

}

