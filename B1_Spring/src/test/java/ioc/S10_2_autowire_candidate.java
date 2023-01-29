package ioc;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import s10.SetterBean;

public class S10_2_autowire_candidate {

    @Test
    public void Test1() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("s10-autowire_candidate.xml");

        // autowire-candidate：设置当前bean在被其他对象作为自动注入对象的时候，是否作为候选bean，默认值是true。
        System.out.println(context.getBean(SetterBean.class)); //@1
        SetterBean.IService service = context.getBean(SetterBean.IService.class); //@2
        System.out.println(service);
    }
}
