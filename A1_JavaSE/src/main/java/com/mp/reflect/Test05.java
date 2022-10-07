package com.mp.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Test05 {

    public static void main(String[] args) throws Exception {
        Class studentClass = Class.forName("com.mp.reflect.Student");

        String className = studentClass.getName();// Class类的getName方法
        System.out.println("完整类名： " + className);
        String simpleName = studentClass.getSimpleName();// Class类的getName方法
        System.out.println("简类名： " + simpleName);

        //获取public修饰的属性
        Field[] fields1 = studentClass.getFields();
        System.out.println(fields1.length);// 2
        //System.out.println(fields1[0].getName() + " " + fields1[1].getName()); //Field类中的getName犯法

        System.out.println("----------------------");

        //获取所有的属性
        Field[] fields2 = studentClass.getDeclaredFields();
        System.out.println(fields2.length);
        for (Field f : fields2){
            System.out.println(f.getName());
        }

        System.out.println("----------------------");

        //获取属性的修饰符列表
        for (Field f : fields2){
            // 获取属性的修饰符列表,返回的修饰符是一个数字，每个数字是修饰符的代号
            // 用Modifier类的toString转换成字符串
            System.out.println(Modifier.toString(f.getModifiers()));
            System.out.println(f.getType().getSimpleName());// 获取属性的类型
            System.out.println(f.getName());// 获取属性的名字
        }
    }

}
