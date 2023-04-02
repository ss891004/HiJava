package ioc;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import s17_5.a1.Bean17_5_1;
import s17_5.a2.Bean17_5_2;
import s17_5.a3.Bean17_5_3;
import s17_5.a4.Bean17_5_4;
import s17_5.a5.Bean17_5_5;
import s17_5.a6.Bean17_5_6;
import s17_5.a7.Bean17_5_7;
import s17_5.a10.Bean17_5_10;
import s17_5.a9.Bean17_5_9;
import s17_5.a8.Bean17_5_8;

public class S17_5_Autowired_Resource_Primary_Qulifier {

    //@Autowired、@Resource、@Primary、@Qulifier

    @Test
    public void Test1() {
        // @Autowired：注入依赖对象
        // 实现依赖注入，spring容器会对bean中所有字段、方法进行遍历，标注有@Autowired注解的，都会进行注入。

        // 按类型找 ->  @Qualifier过滤 -> @Primary -> @Priority -> 根据名称找（字段名称或者参数名称）

        // @Autowired标注在构造器上，通过构造器注入依赖对象
        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(Bean17_5_1.class);
        for (String beanName : aca.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + aca.getBean(beanName));
        }
    }

    @Test
    public void Test2() {
        // @Autowired标注在setter方法上，通过setter方法注入
        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(Bean17_5_2.class);

        for (String beanName : aca.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + aca.getBean(beanName));
        }
    }

    @Test
    public void Test3() {

        // @Autowired标注在方法上，通过方法注入依赖的对象

        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(Bean17_5_3.class);

        for (String beanName : aca.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + aca.getBean(beanName));
        }
    }

    @Test
    public void Test4() {
        // @Autowired标注在方法参数上
        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(Bean17_5_4.class);

        for (String beanName : aca.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + aca.getBean(beanName));
        }
    }

    @Test
    public void Test5() {
        // @Autowired用在字段上
        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(Bean17_5_5.class);

        for (String beanName : aca.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + aca.getBean(beanName));
        }
    }

    @Test
    public void Test6() {

        // @Autowire标注字段，多个候选者的时候，按字段名称注入

        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(Bean17_5_6.class);

        for (String beanName : aca.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + aca.getBean(beanName));
        }
    }

    @Test
    public void Test7() {

        // 将指定类型的所有bean，注入到Collection、Map中

        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(Bean17_5_7.class);

        for (String beanName : aca.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + aca.getBean(beanName));
        }
    }

    ///////////////////////////////////////////////////////////

    @Test
    public void Test8(){

        // 先按Resource的name值作为bean名称找
        // ->按名称（字段名称、方法名称、set属性名称）找
        // ->按类型找
        // ->通过限定符@Qualifier过滤
        // ->@Primary
        // ->@Priority
        // ->根据名称找（字段名称或者方法参数名称）

        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(Bean17_5_8.class);

        for (String beanName : aca.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + aca.getBean(beanName));
        }

    }

    ///////////////////////////////////////////////////////////

    @Test
    public void Test9(){

        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(Bean17_5_9.class);

        for (String beanName : aca.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + aca.getBean(beanName));
        }

    }


    ///////////////////////////////////////////////////////////

    @Test
    public void Test10(){

        // @Primary
        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(Bean17_5_10.class);

        for (String beanName : aca.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + aca.getBean(beanName));
        }

    }



}
