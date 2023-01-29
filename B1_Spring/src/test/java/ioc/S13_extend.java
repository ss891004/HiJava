package ioc;

import s13.ServiceB;
import s13.ServiceC;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class S13_extend {
    // 抽出相同的部分，作为基类，子类通过继承
    @Test
    public void Test1() {

        // 说明 类种的属性serviceA 指向的是同一个对象实例
        // ServiceB{name='Java', serviceA=s13.ServiceA@5e25a92e}
        // ServiceC{name='Python', serviceA=s13.ServiceA@5e25a92e}

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("s13-extend.xml");
        System.out.println(context.getBean(ServiceB.class));
        System.out.println(context.getBean(ServiceC.class));
    }

    @Test
    public void Test2() {
        // bean元素的abstract属性为true的时候可以定义某个bean为一个抽象的bean，
        // 相当于定义了一个bean模板，spring容器并不会创建这个bean，从容器中查找abstract为true的bean的时候，会报错BeanIsAbstractException异常
        // bean元素的parent属性可以指定当前bean的父bean，子bean可以继承父bean中配置信息，也可以自定义配置信息，这样可以覆盖父bean中的配置
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("s13-extend.xml");
        System.out.println(context.getBean("e4"));
        System.out.println(context.getBean("e5"));
    }
}
