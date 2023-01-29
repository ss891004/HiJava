package ioc;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import s00_hi.HelloWorld;


public class S00_hi {

    // 什么是spring容器， 什么是bean
    @Test
    public void Test() {
        //1.bean配置文件位置
        String beanXml = "classpath:s00.xml";
        //2.创建ClassPathXmlApplicationContext容器，给容器指定需要加载的bean配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(beanXml);
        //3.从容器中获取需要的bean
        HelloWorld helloWorld = context.getBean("helloWorld", HelloWorld.class);
        //4.使用对象
        helloWorld.say();
    }


}
