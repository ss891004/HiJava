import S21_componentScan.ScanBean;
import S21_componentScan.ScanBean2;
import S21_componentScan.beans.ScanBean3;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class S21_ComponentScan {


    @Test
    public void Test1() {
        // @ComponentScan用于批量注册bean。

        // 这个注解会让spring去扫描某些包及其子包中所有的类，然后将满足一定条件的类作为bean注册到spring容器容器中。

        //@Component、@Repository、@Service、@Controller

        //value：指定需要扫描的包，如：com.javacode2018
        //basePackages：作用同value；value和basePackages不能同时存在设置，可二选一
        //basePackageClasses：指定一些类，spring容器会扫描这些类所在的包及其子包中的类
        //nameGenerator：自定义bean名称生成器
        //resourcePattern：需要扫描包中的那些资源，默认是：*/\.class，即会扫描指定包中所有的class文件
        //useDefaultFilters：对扫描的类是否启用默认过滤器，默认为true
        //includeFilters：过滤器：用来配置被扫描出来的那些类会被作为组件注册到容器中
        //excludeFilters：过滤器，和includeFilters作用刚好相反，用来对扫描的类进行排除的，被排除的类不会被注册到容器中
        //lazyInit：是否延迟初始化被注册的bean

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

    @Test
    public void test3() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScanBean3.class);
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + context.getBean(beanName));
        }
    }



}
