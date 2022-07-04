package com.hm.reflect;

import java.util.HashSet;
import java.util.Set;

//获得 Class 对象
public class ReflectClassDemo01 {


    public static void main(String[] args) throws ClassNotFoundException {
        //使用 Class 类的 forName 静态方法
        fun1();
        //直接获取某一个对象的 class
        fun2();

        //调用 Object 的 getClass 方法

        fun3();
    }

   public static void fun1() throws ClassNotFoundException {

       Class c1 = Class.forName("com.hm.reflect.ReflectClassDemo01");
       System.out.println(c1.getCanonicalName());
       Class c2 = Class.forName("[D");
       System.out.println(c2.getCanonicalName());
       Class c3 = Class.forName("[[Ljava.lang.String;");
       System.out.println(c3.getCanonicalName());

   }

   public static  void fun2(){
       Boolean b;
       // Class c = b.getClass(); // 编译错误
       Class c1 = Boolean.class;
       System.out.println(c1.getCanonicalName());
       Class c2 = java.io.PrintStream.class;
       System.out.println(c2.getCanonicalName());
       Class c3 = int[][][].class;
       System.out.println(c3.getCanonicalName());
   }

    enum E {
        A, B
    }

    public static void fun3(){
        Class c = "foo".getClass();
        System.out.println(c.getCanonicalName());
        Class c2 = ReflectClassDemo01.E.A.getClass();
        System.out.println(c2.getCanonicalName());
        byte[] bytes = new byte[1024];
        Class c3 = bytes.getClass();
        System.out.println(c3.getCanonicalName());
        Set<String> set = new HashSet<>();
        Class c4 = set.getClass();
        System.out.println(c4.getCanonicalName());
    }



}