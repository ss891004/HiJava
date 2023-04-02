package com.base.b12.generic;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

//泛型类
class Demo<T1, T2> {//@0
//声明了一个泛型类，泛型类中定义了两个泛型变量的类型T1和T2，这两个变量的具体类型，可以在创建对象的时候指定任意具体的类型。
}

public class Demo6 extends Demo<String, Integer> { //@1
    //这个比较特殊了，创建了类Demo6，这个类继承了Demo类，并且注意Demo后面的部分<String, Integer>，这个指定了具体的类型了，此时T1的具体类型就是String类型了，T2对应的具体类型就是Integer类型了。

    public static void main(String[] args) {
        Demo6 demo6 = new Demo6();
        //demo6Class对应的是Demo6的Class对象
        Class<? extends Demo6> demo6Class = demo6.getClass();//获取Demo6对应的Class对象

        //获取Demo6的父类的详细类型信息，包含泛型信息
        Type genericSuperclass = demo6Class.getGenericSuperclass(); //@3
        /**
         * 这行代码比较关键，这个调用了Class类中的getGenericSuperclass方法，这个方法可以获取当前类父类的具体类型信息，如果父类是泛型，则会返回泛型详细信息，泛型类型在java中用ParameterizedType接口表示，所以@3代码返回的类型一定是ParameterizedType接口类型的了
         */


        // 泛型类型用ParameterizedType接口表示，输出看一下是不是这个接口类型的
        System.out.println(genericSuperclass.getClass()); //输出了genericSuperclass变量的类型
        if (genericSuperclass instanceof ParameterizedType) { //@5
            ParameterizedType pt = (ParameterizedType) genericSuperclass;
            System.out.println(pt.getRawType());
            Type[] actualTypeArguments = pt.getActualTypeArguments();
            for (Type actualTypeArgument : actualTypeArguments) {
                System.out.println(actualTypeArgument.getTypeName());
            }
            System.out.println(pt.getOwnerType());
        }
    }

}

