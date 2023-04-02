package com.base.b10.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//为参数指定默认值

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Ann5 {
    String[] name() default {"ss", "spring系列"};//数组类型通过{}指定默认值
    int[] score() default 1; //数组类型参数，默认值只有一个省略了{}符号
    int age() default 30; //默认值为30
    String address(); //未指定默认值
}
@Ann5(age = 32,address = "上海") //age=32对默认值进行了覆盖，并且为address指定了值
public class UseAnno05 {
}
