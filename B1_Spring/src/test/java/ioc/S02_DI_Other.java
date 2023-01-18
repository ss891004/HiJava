package ioc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//其他类型注入
public class S02_DI_Other {

    ClassPathXmlApplicationContext context;

    @Before
    public void before() {
        System.out.println("spring容器准备启动.....");
        //1.bean配置文件位置
        String beanXml = "classpath:s02-DI-Other.xml";
        //2.创建ClassPathXmlApplicationContext容器，给容器指定需要加载的bean配置文件
        this.context = new ClassPathXmlApplicationContext(beanXml);
        System.out.println("spring容器启动完毕！");
    }

    @Test
    public void Test1() {
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println(beanName + ":" + context.getBean(beanName));
        }
    }
}
