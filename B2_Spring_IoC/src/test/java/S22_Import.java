import S22_import.t1.S22Bean;
import S22_import.t2.S22Bean2;
import S22_import.t3.S22Bean3;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class S22_Import {

    // @import  和xml配置的 <import />标签作用一样
    // @Import可以用来批量导入需要注册的各种类，如普通的类、配置类，完后完成普通类和配置类中所有bean的注册。

    @Test
    public void Test1() {

        // value为普通的类
        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(S22Bean.class);

        for (String beanName : aca.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + aca.getBean(beanName));
        }
    }



    @Test
    public void Test2() {

        // value为普通的类
        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(S22Bean2.class);

        for (String beanName : aca.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + aca.getBean(beanName));
        }
    }



    @Test
    public void Test3() {

        // value为普通的类
        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(S22Bean3.class);

        for (String beanName : aca.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + aca.getBean(beanName));
        }
    }

}
