package com.base.b10.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//数组类型参数
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Ann4 {
    String[] name();// name的类型是一个String类型的数组
}
@Ann4(name = {"我是ss", "欢迎和我一起学spring"}) // name有多个值的时候，需要使用{}包含起来
public class UseAnno04 {
    @Ann4(name = "如果只有一个值，{}可以省略") // 如果name只有一个值，{}可以省略
    public class T1 {
    }
}