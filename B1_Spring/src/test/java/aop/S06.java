package aop;

import hm.s06_ProxyFactoryBean.MainConfig1;
import hm.s06_ProxyFactoryBean.MainConfig2;
import hm.s06_ProxyFactoryBean.MainConfig3;
import hm.s06_ProxyFactoryBean.Service1;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class S06 {
    /**
     * ProxyFactoryBean
     * 创建代理，有3个信息比较关键：
     *
     * 需要增强的功能，这个放在通知（Advice）中实现
     * 目标对象（target）：表示你需要给哪个对象进行增强
     * 代理对象（proxy）：将增强的功能和目标对象组合在一起，然后形成的一个代理对象，通过代理对象来访问目标对象，起到对目标对象增强的效果
     */

    // 在spring容器中注册上面这个类的bean，名称为service1，通过代理的方式来对这个bean进行增强，来2个通知
    // 一个前置通知：在调用service1中的任意方法之前，输出一条信息：准备调用xxxx方法
    // 一个环绕通知：负责统计所有方法的耗时。
    @Test
    public void test1(){
        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(MainConfig1.class);
        Service1 s1= aca.getBean("service1Proxy", Service1.class);
        s1.m1();
        s1.m2();
    }

    @Test
    public void test2(){
        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(MainConfig2.class);
        Service1 s1= aca.getBean("service1Proxy", Service1.class);
        s1.m1();
        s1.m2();
    }

    @Test
    public void test3() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig3.class);
        Service1 bean = context.getBean("service1Proxy", Service1.class);
        System.out.println("----------------------");
        bean.m1();
        System.out.println("----------------------");
        bean.m2();
    }
}
