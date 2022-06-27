import s06_lazy_init.LazyInitBean;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class S06_LazyInit {

    @Test
    public void Test1() {


        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-lazy-init.xml");

        System.out.println("~~~~~~~~~~~");

        //在get之后，就实例化此对象
        LazyInitBean li=  context.getBean(LazyInitBean.class);


        System.out.println("+++++++++++");

        System.out.println(li);

        context.close();
    }
}
