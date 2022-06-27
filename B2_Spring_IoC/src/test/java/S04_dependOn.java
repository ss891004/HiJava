import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class S04_dependOn {

            /*
        无依赖的bean创建顺序和定义的顺序一致，销毁顺序刚好相反。
        通过构造器强依赖的bean，会先创建构造器参数中对应的bean，然后才会创建当前bean，销毁顺序刚好相反。
        depend-on可以指定档期bean依赖的bean，通过这个可以确保depend-on指定的bean在当前bean创建之前先创建好，销毁顺序刚好相反。
        */

    @Test
    public void Test1(){

        //无依赖bean创建和销毁的顺序
        //  bean对象的创建顺序和bean xml中定义的顺序一致
        //  bean销毁的顺序和bean xml中定义的顺序相反

        System.out.println("容器启动中!");
        ClassPathXmlApplicationContext context =new ClassPathXmlApplicationContext("spring-dependOn.xml");
        System.out.println("容器启动完毕，准备关闭spring容器!");
        context.close();
        System.out.println("spring容器已关闭!");
    }


    // 通过构造器强依赖bean创建和销毁顺序
    @Test
    public void Test2(){
        // bean创建的顺序与xml中的顺序无关，销毁的顺序与创建的顺序相反
        System.out.println("容器启动中!");
        ClassPathXmlApplicationContext context =new ClassPathXmlApplicationContext("spring-dependOn2.xml");
        System.out.println("容器启动完毕，准备关闭spring容器!");
        context.close();
        System.out.println("spring容器已关闭!");
    }

    // 通过 depend-on 改变对象创建的顺序
    @Test
    public void Test3(){

        System.out.println("容器启动中!");
        ClassPathXmlApplicationContext context =new ClassPathXmlApplicationContext("spring-dependOn3.xml");
        System.out.println("容器启动完毕，准备关闭spring容器!");
        context.close();
        System.out.println("spring容器已关闭!");
    }

    // 通过setter 方式注入的对象，创建对象的顺序，和销毁对象的顺序
    @Test
    public void Test4(){

        System.out.println("容器启动中!");
        ClassPathXmlApplicationContext context =new ClassPathXmlApplicationContext("spring-dependOn4.xml");
        System.out.println("容器启动完毕，准备关闭spring容器!");
        context.close();
        System.out.println("spring容器已关闭!");
    }

}
