package ioc;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import s14.Serv1;
import s14.Serv2;
import s14.Serv3;

public class S14_lookup_method_replaced_method {

    //单例bean, 多例bean

    //lookup-method：方法查找，可以对指定的bean的方法进行拦截，然后从容器中查找指定的bean作为被拦截方法的返回值
    //replaced-method：方法替换，可以实现bean方法替换的效果，整体来说比lookup-method更灵活一些
    @Test
    public void Test1() {
        // lookup-method

        // Serv1 每次都是实例化新对象
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("s14-lookup_method.xml");
        System.out.println(context.getBean(Serv1.class)); // 每取一次，都是新的实例
        System.out.println(context.getBean(Serv1.class));

        System.out.println(context.getBean(Serv2.class)); // 每取一次，都是同一个实例
        System.out.println(context.getBean(Serv2.class));
    }

    @Test
    public void Test2() {
        // lookup-method

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("s14-lookup_method2.xml");
        System.out.println(context.getBean(Serv1.class));
        System.out.println(context.getBean(Serv1.class));
        Serv3 xxx = context.getBean(Serv3.class);
        xxx.make();
        xxx.make();

    }

    @Test
    public void Test3() {

        // replaced-method：方法替换，比如我们要调用serviceB中的getServiceA的时候，
        // 我们可以对serviceB这个bean中的getServiceA方法进行拦截，把这个调用请求转发到一个替换者处理。
        // 这就是replaced-method可以实现的功能，比lookup-method更强大更灵活。

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("s14_replaced-method.xml");

        Serv3 xxx = context.getBean(Serv3.class);
        xxx.make();
        xxx.make();

    }

}
