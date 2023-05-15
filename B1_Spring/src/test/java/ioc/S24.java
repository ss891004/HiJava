package ioc;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class S24 {


    /**
     * 父子容器特点
     *
     * 父容器和子容器是相互隔离的，他们内部可以存在名称相同的bean
     * 子容器可以访问父容器中的bean，而父容器不能访问子容器中的bean
     * 调用子容器的getBean方法获取bean的时候，会沿着当前容器开始向上面的容器进行查找，直到找到对应的bean为止
     * 子容器中可以通过任何注入方式注入父容器中的bean，而父容器中是无法注入子容器中的bean，原因是第2点
     */

    //父子容器
    @Test
    public void test(){
        //创建父容器
        AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
//启动父容器
        parentContext.refresh();
//创建子容器
        AnnotationConfigApplicationContext childContext = new AnnotationConfigApplicationContext();
//给子容器设置父容器
        childContext.setParent(parentContext);
//启动子容器
        childContext.refresh();
    }
}
