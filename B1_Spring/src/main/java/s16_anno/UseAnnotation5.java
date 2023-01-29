package s16_anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//为参数指定默认值

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Ann5 {
    String[] name() default {"路人甲java", "spring系列"};//@1
    int[] score() default 1; //@2
    int age() default 30; //@3
    String address(); //@4
}
@Ann5(age = 32,address = "上海") //@5
public class UseAnnotation5 {
}