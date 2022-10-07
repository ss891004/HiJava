package com.mp.reflect;

 public class Test02 {
     public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
         // 下面这段代码是以反射机制的方式创建对象。

         // 通过反射机制，获取Class，通过Class来实例化对象
         Class c = Class.forName("com.mp.reflect.User");
         // newInstance() 这个方法会调用User这个类的无参数构造方法，完成对象的创建。
         // 重点是：newInstance()调用的是无参构造，必须保证无参构造是存在的！
         Object obj = c.newInstance();
         System.out.println(obj);
     }
 }

