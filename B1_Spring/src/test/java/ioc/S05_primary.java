package ioc;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class S05_primary {

    @Test
    public void Test1() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("s05-primary.xml");

        //No qualifying bean of type 's05_primary.PrimaryBean$IService' available: expected single matching bean but found 2: s1,s2
        // 当希望从容器中获取到一个bean对象的时候，容器中却找到了多个匹配的bean，此时spring不知道如何选择了，处于懵逼状态，就会报这个异常。

        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println(beanName + ":" + context.getBean(beanName));
        }

    }
}
