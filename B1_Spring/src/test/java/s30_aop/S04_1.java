package s30_aop;

import s30_aop.s04_cglibaop.IService1;
import s30_aop.s04_cglibaop.IService2;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;


// 为多个接口创建代理
public class S04_1 {

    @Test
    public void test() {
        Enhancer enhancer = new Enhancer();

        enhancer.setInterfaces(new Class[]{IService1.class, IService2.class});

        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            System.out.println("调用方法:" + method.getName());
            return null;
        });

        Object proxy = enhancer.create();

        if (proxy instanceof IService1) {
            ((IService1) proxy).m1();
        }

        if (proxy instanceof IService2) {
            ((IService2) proxy).m2();
        }

        //看一下代理对象的类型
        System.out.println(proxy.getClass());
        //看一下代理类实现的接口
        System.out.println("创建代理类实现的接口如下：");
        for (Class<?> cs : proxy.getClass().getInterfaces()) {
            System.out.println(cs);
        }
    }
}
