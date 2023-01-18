package ioc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


//xml中bean定义详解
public class S01_Bean {

    @Test
    public void Test() {
        //1.初始化，会将xml文件中所有bean对象进行初始化
        //2.创建ClassPathXmlApplicationContext容器，给容器指定需要加载的bean配置文件
        ApplicationContext ac = new ClassPathXmlApplicationContext("s01.xml");
        //获取bean对象
        System.out.println(">>>>>>>>>>");

        for (String beanName : ac.getBeanDefinitionNames()) {
            System.out.println(beanName + ":" + ac.getBean(beanName));
        }
        //3.从容器中获取需要的bean

    }

    //通过反射调用构造方法创建bean对象


    //通过静态工厂方法创建bean对象


    //通过实例工厂方法创建bean对象


    //通过FactoryBean创建bean对象

}
