package s16_anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//数组类型参数

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Ann4 {
    String[] name();//@1
}
@Ann4(name = {"我是路人甲java", "欢迎和我一起学spring"}) //@2
public class UseAnnotation4 {
    @Ann4(name = "如果只有一个值，{}可以省略") //@3
    public class T1 {
    }
}