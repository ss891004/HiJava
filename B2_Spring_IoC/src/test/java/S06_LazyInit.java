import s06_lazy_init.LazyInitBean;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class S06_LazyInit {

    @Test
    public void Test1() {

        //LazyInitBean在容器启动过程中并没有创建，当我们调用context.getBean方法的时候，LazyInitBean才被创建的。
        System.out.println("spring容器启动中...");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("s06-lazy-init.xml");
        System.out.println("spring容器启动完毕...");
        System.out.println("从容器中开始查找LazyInitBean");
        LazyInitBean lazyInitBean = context.getBean(LazyInitBean.class);
        System.out.println("LazyInitBean:" + lazyInitBean);
    }

    @Test
    public void Test2(){

        // 由于actualTimeDependencyLazyBean为实时初始化的bean，而这个bean在创建过程中需要用到lazyInitBean，
        // 此时容器会去查找lazyInitBean这个bean，然后会进行初始化，所以这2个bean都在容器启动过程中被创建的。

        System.out.println("spring容器启动中...");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("s06-lazy-init2.xml");
        //启动spring容器
        System.out.println("spring容器启动完毕...");
    }
}
