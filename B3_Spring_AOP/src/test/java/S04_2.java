
import hm.s04_cglibaop.*;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;


//为类和接口同时创建代理
public class S04_2 {

    @Test
    public void test() {

        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(Service.class);

        enhancer.setInterfaces(new Class[]{IService1.class, IService2.class});

        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            System.out.println("调用方法:" + method.getName());
            return  methodProxy.invokeSuper(o, objects); //调用父类中的方法
        });

        Object proxy = enhancer.create();

        if (proxy instanceof Service) {
            Service s =  (Service) proxy;
            s.m1();
            s.m2();
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
