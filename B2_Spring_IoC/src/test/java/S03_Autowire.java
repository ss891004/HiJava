import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//依赖注入之自动注入
public class S03_Autowire {
    ClassPathXmlApplicationContext context;

    @Before
    public void before() {
        System.out.println("spring容器准备启动.....");
        //1.bean配置文件位置
        String beanXml = "classpath:s03-Autowire.xml";
        //2.创建ClassPathXmlApplicationContext容器，给容器指定需要加载的bean配置文件
        this.context = new ClassPathXmlApplicationContext(beanXml);
        System.out.println("spring容器启动完毕！");
    }

    @Test
    public void Test1() {
        //自动注入是采用约定大约配置的方式来实现的，程序和spring容器之间约定好，遵守某一种都认同的规则，来实现自动注入。

        // 3种自动注入方式详解及案例


        // 按名称自动注入
        System.out.println(context.getBean("diByName1"));
        System.out.println(context.getBean("diByName2"));


        // 按类型自动注入
        System.out.println(context.getBean("diByType"));

        // 按构造器进行自动注入
        System.out.println(context.getBean("diByComstructor"));


        // 按类型自动注入某种类型的所有bean给List和Map（重点）


    }

}
