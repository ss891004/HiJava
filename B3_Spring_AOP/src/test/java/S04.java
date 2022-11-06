import hm.s04_jdkaop.CostTimeInvocationHandler;
import hm.s04_jdkaop.IService1;
import hm.s04_jdkaop.IService2;
import hm.s04_jdkaop.Service;
import org.junit.Test;

import java.lang.reflect.Proxy;

public class S04 {

    /**
     * spring事务管理：@Transactional
     * spring异步处理：@EnableAsync
     * spring缓存技术的使用：@EnableCaching
     * spring中各种拦截器：@EnableAspectJAutoProxy
     */

    /**
     * spring中的aop功能主要是通过2种代理来实现的
     * jdk动态代理
     * cglib代理
     */

    @Test
    public void test1() {

        Service target = new Service();

        CostTimeInvocationHandler cst = new CostTimeInvocationHandler(target);

        //创建代理对象
        Object proxyObject = Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                new Class[]{IService1.class, IService2.class}, //创建的代理对象实现了2个接口
                cst);
        //判断代理对象是否是Service类型的，肯定是false咯
        System.out.println(String.format("proxyObject instanceof Service = %s", proxyObject instanceof Service));
        //判断代理对象是否是IService1类型的，肯定是true
        System.out.println(String.format("proxyObject instanceof IService1 = %s", proxyObject instanceof IService1));
        //判断代理对象是否是IService2类型的，肯定是true
        System.out.println(String.format("proxyObject instanceof IService2 = %s", proxyObject instanceof IService2));
        //将代理转换为IService1类型
        IService1 service1 = (IService1) proxyObject;
        //调用IService2的m1方法
        service1.m1();
        //将代理转换为IService2类型
        IService2 service2 = (IService2) proxyObject;
        //调用IService2的m2方法
        service2.m2();
        //输出代理的类型
        System.out.println("代理对象的类型:" + proxyObject.getClass());


    }


    /**
     * cglib整个过程如下
     * Cglib根据父类,Callback, Filter 及一些相关信息生成key
     * 然后根据key 生成对应的子类的二进制表现形式
     * 使用ClassLoader装载对应的二进制,生成Class对象,并缓存
     * 最后实例化Class对象,并缓存
     */


}
