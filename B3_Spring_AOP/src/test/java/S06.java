import hm.s06_aop.MainConfig1;
import hm.s06_aop.MainConfig2;
import hm.s06_aop.Service1;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class S06 {

    // 一个前置通知：在调用service1中的任意方法之前，输出一条信息：准备调用xxxx方法
    //一个环绕通知：复制统计所有方法的耗时。

    @Test
    public void test1(){
        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(MainConfig1.class);

        Service1 s1= aca.getBean("service1Proxy", Service1.class);

        s1.m1();

        s1.m2();

    }

    @Test
    public void test2(){
        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(MainConfig2.class);

        Service1 s1= aca.getBean("service1Proxy", Service1.class);

        s1.m1();

        s1.m2();

    }


}
