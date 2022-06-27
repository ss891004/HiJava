import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import s24_annotation.a1.S24Bean;
import s24_annotation.a2.S24Bean2;
import s24_annotation.a3.S24Bean3;
import s24_annotation.a4.S24Bean4;
import s24_annotation.a5.S24Bean5;
import s24_annotation.a6.S24Bean6;
import s24_annotation.a7.S24Bean7;
import s24_annotation.p1.S24Bean10;
import s24_annotation.q1.S24Bean9;
import s24_annotation.r1.S24Bean8;

public class S24_Annotation {

    //@Autowired、@Resource、@Primary、@Qulifier

    @Test
    public void Test1() {


        // @Autowired：注入依赖对象
        // 实现依赖注入，spring容器会对bean中所有字段、方法进行遍历，标注有@Autowired注解的，都会进行注入。

        // 按类型找->通过限定符@Qualifier过滤->@Primary->@Priority->根据名称找（字段名称或者参数名称）


        // @Autowired标注在构造器上，通过构造器注入依赖对象
        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(S24Bean.class);

        for (String beanName : aca.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + aca.getBean(beanName));
        }


    }

    @Test
    public void Test2() {

        // @Autowired标注在setter方法上，通过setter方法注入

        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(S24Bean2.class);

        for (String beanName : aca.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + aca.getBean(beanName));
        }
    }

    @Test
    public void Test3() {

        // @Autowired标注在方法上，通过方法注入依赖的对象

        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(S24Bean3.class);

        for (String beanName : aca.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + aca.getBean(beanName));
        }
    }

    @Test
    public void Test4() {

        // @Autowired标注在方法参数上

        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(S24Bean4.class);

        for (String beanName : aca.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + aca.getBean(beanName));
        }
    }

    @Test
    public void Test5() {

        // @Autowired用在字段上

        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(S24Bean5.class);

        for (String beanName : aca.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + aca.getBean(beanName));
        }
    }

    @Test
    public void Test6() {

        // @Autowire标注字段，多个候选者的时候，按字段名称注入

        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(S24Bean6.class);

        for (String beanName : aca.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + aca.getBean(beanName));
        }
    }

    @Test
    public void Test7() {

        // 将指定类型的所有bean，注入到Collection、Map中

        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(S24Bean7.class);

        for (String beanName : aca.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + aca.getBean(beanName));
        }
    }

    ///////////////////////////////////////////////////////////

    @Test
    public void Test8(){

        // 先按Resource的name值作为bean名称找->按名称（字段名称、方法名称、set属性名称）找->按类型找->通过限定符@Qualifier过滤->@Primary->@Priority->根据名称找（字段名称或者方法参数名称）

        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(S24Bean8.class);

        for (String beanName : aca.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + aca.getBean(beanName));
        }

    }

    ///////////////////////////////////////////////////////////

    @Test
    public void Test9(){

        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(S24Bean9.class);

        for (String beanName : aca.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + aca.getBean(beanName));
        }

    }


    ///////////////////////////////////////////////////////////

    @Test
    public void Test10(){

        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(S24Bean10.class);

        for (String beanName : aca.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + aca.getBean(beanName));
        }

    }



}
