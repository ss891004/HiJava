package ioc;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import s17_6.a1.Bean17_6_1;
import s17_6.a2.Bean17_6_2;
import s17_6.a1.Serv3;

public class S17_6_Scope_DependsOn_ImportResource_Lazy {

    //@Scope、@DependsOn、@ImportResource、@Lazy

    //@Scope：用来定义bean 的作用域；2种用法：第1种：标注在类上；第2种：和@Bean一起标注在方法上
    //@DependsOn：用来指定当前bean依赖的bean，可以确保在创建当前bean之前，先将依赖的bean创建好；2种用法：第1种：标注在类上；第2种：和@Bean一起标注在方法上
    //@ImportResource：标注在配置类上，用来引入bean定义的配置文件
    //@Lazy：让bean延迟初始化；常见3种用法：第1种：标注在类上；第2种：标注在配置类上，会对配置类中所有的@Bean标注的方法有效；第3种：和@Bean一起标注在方法上

    @Test
    public void Test1() {


    }

    @Test
    public void Test2() {
        // @DependsOn等效于bean xml中的bean元素中的depend-on属性。

        // spring在创建bean的时候，如果bean之间没有依赖关系，那么spring容器很难保证bean实例创建的顺序，
        // 如果想确保容器在创建某些bean之前，需要先创建好一些其他的bean，可以通过@DependsOn来实现，@DependsOn可以指定当前bean依赖的bean，
        // 通过这个可以确保@DependsOn指定的bean在当前bean创建之前先创建好

        // @DependsOn和@Compontent一起使用在类上
        // @DependsOn和@Bean一起标注在方法上

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Bean17_6_1.class);
        System.out.println(context.getBean(Serv3.class));
    }

    @Test
    public void Test3(){
        /**
         * 资源文件路径的写法
         * 通常我们的项是采用maven来组织的，配置文件一般会放在resources目录，这个目录中的文件被编译之后会在target/classes目录中。
         *
         * spring中资源文件路径最常用的有2种写法：
         *
         *  以classpath:开头：检索目标为当前项目的classes目录
         *  以classpath*:开头：检索目标为当前项目的classes目录，以及项目中所有jar包中的目录，如果你确定jar不是检索目标，就不要用这种方式，由于需要扫描所有jar包，所以速度相对于第一种会慢一些
         *
         *  几种常见的如下：
         * 相对路径的方式
         * /：绝对路径的方式
         * *：文件通配符的方式
         *
         * *：目录通配符的方式
         * **：递归任意子目录的方式
         *
         */

        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(Bean17_6_2.class);

        for (String beanName : aca.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + aca.getBean(beanName));
        }
    }

    @Test
    public void Test4() {

        // @Lazy等效于bean xml中bean元素的lazy-init属性，可以实现bean的延迟初始化。

        // 和@Compontent一起标注在类上，可以是这个类延迟初始化
        // 和@Configuration一起标注在配置类中，可以让当前配置类中通过@Bean注册的bean延迟初始化
        // 和@Bean一起使用，可以使当前bean延迟初始化
    }




    }
