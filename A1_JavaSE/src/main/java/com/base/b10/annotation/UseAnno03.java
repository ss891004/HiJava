package com.base.b10.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//一个参数为value的注解，可以省略参数名称
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Ann3 {
    String value();//注解之后一个参数，名称为value
}
@Ann3("我是ss") //使用注解，参数名称value省略了
public class UseAnno03 {
}