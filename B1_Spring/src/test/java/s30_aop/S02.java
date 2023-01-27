package s30_aop;

import s30_aop.s01_proxy.IService;
import s30_aop.s01_proxy.ServiceA;
import s30_aop.s01_proxy.ServiceB;
import s30_aop.s02_jdk.CostTimeInvocationHandler;
import s30_aop.s02_jdk.IUserService;
import s30_aop.s02_jdk.UserService;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class S02 {
    //通用代理的2种实现：
    //  jdk动态代理
    //  cglib代理


    // 如果接口众多，那就需要一个通用的代理类，主要涉及到下面2个类
    //java.lang.reflect.Proxy
    //java.lang.reflect.InvocationHandler

    // jdk动态代理
    // 创建代理：方式一
    // 1.调用Proxy.getProxyClass方法获取代理类的Class对象
    // 2.使用InvocationHandler接口创建代理类的处理器
    // 3.通过代理类和InvocationHandler创建代理对象
    // 4.上面已经创建好代理对象了，接着我们就可以使用代理对象了

    // jdk中的Proxy只能为接口生成代理类。
    // Proxy类中提供的几个常用的静态方法大家需要掌握
    // 通过Proxy创建代理对象，当调用代理对象任意方法时候，会被InvocationHandler接口中的invoke方法进行处理，这个接口内容是关键

    @Test
    public void jdk_proxy1() throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException,
            InstantiationException {
        // 1. 获取接口对应的代理类
        Class<IService> proxyClass = (Class<IService>)
                Proxy.getProxyClass(IService.class.getClassLoader(),
                IService.class);

        // 2. 创建代理类的处理器
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("我是InvocationHandler，被调用的方法是：" + method.getName());
                return null;
            }
        };

        // 3. 创建代理实例
        IService proxyService = proxyClass.getConstructor(InvocationHandler.class).newInstance(invocationHandler);

        // 4. 调用代理的方法
        proxyService.m1();
        proxyService.m2();
        proxyService.m3();
    }


    // 创建代理：方式二
    // 1.使用InvocationHandler接口创建代理类的处理器
    // 2.使用Proxy类的静态方法newProxyInstance直接创建代理对象
    // 3.使用代理对象

    @Test
    public void jdk_proxy2() {
        // 1. 创建代理类的处理器
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("我是InvocationHandler，被调用的方法是：" + method.getName());
                return null;
            }
        };
        // 2. 创建代理实例
        IService proxyService = (IService) Proxy.newProxyInstance(IService.class.getClassLoader(), new Class[]{IService.class}, invocationHandler);
        // 3. 调用代理的方法
        proxyService.m1();
        proxyService.m2();
        proxyService.m3();
    }



    // 任意接口中的方法耗时统计
    @Test
    public void test1() {
        IService serviceA = CostTimeInvocationHandler.createProxy(new ServiceA(), IService.class);
        IService serviceA1 = new ServiceA();
        IService serviceB = CostTimeInvocationHandler.createProxy(new ServiceB(), IService.class);
        serviceA.m1();
        serviceA.m2();
        serviceA.m3();
        serviceB.m1();
        serviceB.m2();
        serviceB.m3();
    }

    // 任意接口中的方法耗时统计
    @Test
    public void test2() {
        IUserService userService = CostTimeInvocationHandler.createProxy(new UserService(), IUserService.class);
        userService.insert("Java～～");
    }



}
