import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.*;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import s30_BeanLife.*;

import java.util.Arrays;

public class S30_BeanLife2 {

//阶段1：Bean元信息配置阶段
    //API的方式
    //Xml文件方式
    //properties文件的方式
    //注解的方式

//阶段2：Bean元信息解析阶段
    // Bean元信息的解析就是将各种方式定义的bean配置信息解析为BeanDefinition对象。
        // xml文件定义bean的解析
        // properties文件定义bean的解析
        // 注解方式定义bean的解析

//阶段3：将Bean注册到容器中
//阶段4：BeanDefinition合并阶段
//阶段5：Bean Class加载阶段
//阶段6：Bean实例化阶段（2个小阶段）
    //Bean实例化前阶段
    //Bean实例化阶段

//阶段7：合并后的BeanDefinition处理
//阶段8：属性赋值阶段（3个小阶段）
    //Bean实例化后阶段
    //Bean属性赋值前阶段
    //Bean属性赋值阶段
//阶段9：Bean初始化阶段（5个小阶段）
    //Bean Aware接口回调阶段
    //Bean初始化前阶段
    //Bean初始化阶段
    //Bean初始化后阶段

//阶段10：所有单例bean初始化完成后阶段
//阶段11：Bean的使用阶段
//阶段12：Bean销毁前阶段
//阶段13：Bean销毁阶段


    /**
     * xml方式bean配置信息解析
     */
    @Test
    public void test1() {
        //定义一个spring容器，这个容器默认实现了BeanDefinitionRegistry，所以本身就是一个bean注册器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        //定义一个xml的BeanDefinition读取器，需要传递一个BeanDefinitionRegistry（bean注册器）对象
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(factory);

        //指定bean xml配置文件的位置
        String location = "classpath:s30-2.xml";
        //通过XmlBeanDefinitionReader加载bean xml文件，然后将解析产生的BeanDefinition注册到容器容器中
        int countBean = xmlBeanDefinitionReader.loadBeanDefinitions(location);
        System.out.println(String.format("共注册了 %s 个bean", countBean));

        //打印出注册的bean的配置信息
        for (String beanName : factory.getBeanDefinitionNames()) {
            //通过名称从容器中获取对应的BeanDefinition信息
            BeanDefinition beanDefinition = factory.getBeanDefinition(beanName);
            //获取BeanDefinition具体使用的是哪个类
            String beanDefinitionClassName = beanDefinition.getClass().getName();
            //通过名称获取bean对象
            Object bean = factory.getBean(beanName);
            //打印输出
            System.out.println(beanName + ":");
            System.out.println("    beanDefinitionClassName：" + beanDefinitionClassName);
            System.out.println("    beanDefinition：" + beanDefinition);
            System.out.println("    bean：" + bean);
        }
    }


    @Test
    public void test3() {
        //定义一个spring容器，这个容器默认实现了BeanDefinitionRegistry，所以本身就是一个bean注册器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        //定义一个注解方式的BeanDefinition读取器，需要传递一个BeanDefinitionRegistry（bean注册器）对象
        AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(factory);
        //通过PropertiesBeanDefinitionReader加载bean properties文件，然后将解析产生的BeanDefinition注册到容器容器中
        annotatedBeanDefinitionReader.register(Service1.class, Service2.class);


        // 没有下面的执行，service2中的service1属性是null，没有注入到
        factory.getBeansOfType(BeanPostProcessor.class).values().forEach(factory::addBeanPostProcessor);

        //打印出注册的bean的配置信息
        for (String beanName : new String[]{"service1", "service2"}) {
            //通过名称从容器中获取对应的BeanDefinition信息
            BeanDefinition beanDefinition = factory.getBeanDefinition(beanName);
            //获取BeanDefinition具体使用的是哪个类
            String beanDefinitionClassName = beanDefinition.getClass().getName();
            //通过名称获取bean对象
            Object bean = factory.getBean(beanName);
            //打印输出
            System.out.println(beanName + ":");
            System.out.println("    beanDefinitionClassName：" + beanDefinitionClassName);
            System.out.println("    beanDefinition：" + beanDefinition);
            System.out.println("    bean：" + bean);
        }
    }




}
