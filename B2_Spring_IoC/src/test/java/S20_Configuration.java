import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import s20_configuration.ConfigBean;
import s20_configuration.ConfigBean1;
import s20_configuration.ConfigBean2;

import java.util.Arrays;

public class S20_Configuration {


    @Test
    public void Test1() {

        // @Configuration注解  => 等同于一个bean xml配置文件

        // @Bean
        // 通过AnnotationConfigApplicationContext来加载配置类ConfigBean，会将配置类中所有的bean注册到spring容器中
        // for循环中输出了bean名称、别名、bean对象
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigBean.class);
        for (String beanName : context.getBeanDefinitionNames()) {
            //别名
            String[] aliases = context.getAliases(beanName);
            System.out.println(String.format("bean名称:%s,别名:%s,bean对象:%s",
                    beanName,
                    Arrays.asList(aliases),
                    context.getBean(beanName)));
        }
    }

    @Test
    public void Test11() {

        // 通过AnnotationConfigApplicationContext来加载配置类ConfigBean，会将配置类中所有的bean注册到spring容器中
        // for循环中输出了bean名称、别名、bean对象
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigBean1.class);
        for (String beanName : context.getBeanDefinitionNames()) {
            //别名
            String[] aliases = context.getAliases(beanName);
            System.out.println(String.format("bean名称:%s,别名:%s,bean对象:%s",
                    beanName,
                    Arrays.asList(aliases),
                    context.getBean(beanName)));
        }
    }
    /*
     * 对比最后3行，可以看出：有没有@Configuration注解，@Bean都会起效，都会将@Bean修饰的方法作为bean注册到容器中
     * 两个内容的第一行有点不一样，被@Configuration修饰的bean最后输出的时候带有EnhancerBySpringCGLIB的字样，
     * 而没有@Configuration注解的bean没有Cglib的字样；有EnhancerBySpringCGLIB字样的说明这个bean被cglib处理过的，变成了一个代理对象。
     * */

////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void Test2() {

        // @Configuration注解修饰的类，会被spring通过cglib做增强处理，通过cglib会生成一个代理对象，代理会拦截所有被@Bean注解修饰的方法，可以确保一些bean是单例的
        // 不管@Bean所在的类上是否有@Configuration注解，都可以将@Bean修饰的方法作为一个bean注册到spring容器中

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigBean2.class);
        for (String beanName : context.getBeanDefinitionNames()) {
            //别名
            String[] aliases = context.getAliases(beanName);
            System.out.println(String.format("bean名称:%s,别名:%s,bean对象:%s",
                    beanName,
                    Arrays.asList(aliases),
                    context.getBean(beanName)));
        }
    }

}
