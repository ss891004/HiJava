package ioc;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class S09_dependOn {
    @Test
    public void Test1() {

        //无依赖bean创建和销毁的顺序
        //  bean对象的创建顺序和bean xml中定义的顺序一致
        //  bean销毁的顺序和bean xml中定义的顺序相反

        System.out.println("容器启动中!");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("s09-dependOn.xml");
        System.out.println("容器启动完毕，准备关闭spring容器!");
        context.close();
        System.out.println("spring容器已关闭!");
    }


    @Test
    public void Test2() {
        // 通过构造器强依赖的bean，会先创建构造器参数中对应的bean，然后才会创建当前bean，销毁顺序刚好相反。
        System.out.println("容器启动中!");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("s09-dependOn2.xml");
        System.out.println("容器启动完毕，准备关闭spring容器!");
        context.close();
        System.out.println("spring容器已关闭!");
    }

    @Test
    public void Test3() {
        //depend-on可以指定当前bean依赖的所有bean，通过这个可以确保depend-on指定的bean在当前bean创建之前先创建好，销毁顺序刚好相反。
        System.out.println("容器启动中!");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("s09-dependOn3.xml");
        System.out.println("容器启动完毕，准备关闭spring容器!");
        context.close();
        System.out.println("spring容器已关闭!");
    }


    @Test
    public void Test4() {
        // 通过setter 方式注入的对象，创建对象的顺序和销毁对象的顺序 一样
        System.out.println("容器启动中!");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("s09-dependOn4.xml");
        System.out.println("容器启动完毕，准备关闭spring容器!");
        context.close();
        System.out.println("spring容器已关闭!");
    }

}
