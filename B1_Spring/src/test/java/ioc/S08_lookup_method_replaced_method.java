package ioc;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import s07_extend.ServiceB;
import s07_extend.ServiceC;
import s08_lookup.S08_1;
import s08_lookup.S08_2;
import s08_lookup.S08_3;

public class S08_lookup_method_replaced_method {

    //单例bean中使用多例bean
    //lookup-method：方法查找，可以对指定的bean的方法进行拦截，然后从容器中查找指定的bean作为被拦截方法的返回值
    //replaced-method：方法替换，可以实现bean方法替换的效果，整体来说比lookup-method更灵活一些
    @Test
    public void Test1() {
        // lookup-method

        // S08_1 每次都是实例化新对象
        // S08_2是 单例，其中的属性 s08_1 也是单例。
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("s08-lookup_method.xml");
        System.out.println(context.getBean(S08_1.class));
        System.out.println(context.getBean(S08_1.class));
        System.out.println(context.getBean(S08_2.class));
        System.out.println(context.getBean(S08_2.class));
    }

    @Test
    public void Test2() {
        // lookup-method

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("s08-lookup_method2.xml");
        System.out.println(context.getBean(S08_1.class));
        System.out.println(context.getBean(S08_1.class));
        S08_3 xxx = context.getBean(S08_3.class);
        xxx.make();
        xxx.make();

    }

    //replaced-method

}
