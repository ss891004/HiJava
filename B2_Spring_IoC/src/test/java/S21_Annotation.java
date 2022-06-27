import S21_annotation.ScanBean;
import S21_annotation.ScanBean2;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import s20_annotation.ConfigBean;
import s20_annotation.ConfigBean1;
import s20_annotation.ConfigBean2;

import java.util.Arrays;

public class S21_Annotation {


    @Test
    public void Test1() {
        // @ComponentScan用于批量注册bean。
        // 这个注解会让spring去扫描某些包及其子包中所有的类，然后将满足一定条件的类作为bean注册到spring容器容器中。

        //@Component、@Repository、@Service、@Controller

        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(ScanBean.class);
        for (String beanName : aca.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + aca.getBean(beanName));
        }

    }

    @Test
    public void Test2() {
        //指定扫描哪些包
        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(ScanBean2.class);
        for (String beanName : aca.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + aca.getBean(beanName));
        }

    }



}
