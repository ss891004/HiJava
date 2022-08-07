package com.hm.reflect;

import java.util.Date;

public class Test01 {
    public static void main(String[] args) throws ClassNotFoundException {
        /*
        第一种方式：Class.forName()
            1、静态方法
            2、方法的参数是一个字符串。
            3、字符串需要的是一个完整类名。
            4、完整类名必须带有包名。java.lang包也不能省略。
         */
        Class c1 = Class.forName("java.lang.String"); // c1代表String.class文件，或者说c1代表String类型。
        Class c2 = Class.forName("java.lang.Integer"); // c2代表Integer类型
        Class c3 = Class.forName("java.util.Date"); // c3代表Date类型


        // 第二种方式：java中任何一个对象都有一个方法：getClass()
        String a = "abc";
        Class c4 = a.getClass(); // c4代表String.class字节码文件；c4代表String类型。
        System.out.println(c4 == c1);//true（==判断的是对象的内存地址。）

        Date time = new Date();
        Class c5 = time.getClass();
        System.out.println(c5 == c3);//true(c5和c3两个变量中保存的内存地址都是一样的，都指向方法区中的字节码文件。)

        // 第三种方式：java语言中任何一种类型，包括基本数据类型，它都有.class属性。
        Class i = Integer.class; //i代表Integer类型
        Class d = Date.class; // d代表Date类型
        Class f = float.class; //f代表float类型

        System.out.println(i == c2);//true
    }

}
