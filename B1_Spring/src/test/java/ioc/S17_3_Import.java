package ioc;

import s17_3_import.t1.Bean1;
import s17_3_import.t2.Bean2;
import s17_3_import.t3.Bean3;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class S17_3_Import {

    // @import  和xml配置的 <import />标签作用一样
    // @Import可以用来批量导入需要注册的各种类，如普通的类、配置类，完后完成普通类和配置类中所有bean的注册。

    @Test
    public void Test1() {
        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(Bean1.class);

        for (String beanName : aca.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + aca.getBean(beanName));
        }
    }

    @Test
    public void Test2() {
        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(Bean2.class);

        for (String beanName : aca.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + aca.getBean(beanName));
        }
    }

    @Test
    public void Test3() {

        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(Bean3.class);

        for (String beanName : aca.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + aca.getBean(beanName));
        }
    }

}
