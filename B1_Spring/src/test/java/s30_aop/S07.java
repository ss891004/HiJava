package s30_aop;

import s30_aop.s07_AspectJ.a1.Aspect1;
import s30_aop.s07_AspectJ.a2.Aspect2;
import s30_aop.s07_AspectJ.a2.C2;
import s30_aop.s07_AspectJ.a1.Service1;
import s30_aop.s07_AspectJ.a3.Aspect3;
import s30_aop.s07_AspectJ.a3.C3;
import s30_aop.s07_AspectJ.a4.Aspect4;
import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.util.ClassUtils;

// AspectJProxyFactory
public class S07 {
    /**
     * target
     * 用来表示目标对象，即需要通过aop来增强的对象。
     * <p>
     * proxy
     * 代理对象，target通过aop增强之后生成的代理对象。
     * <p>
     * AspectJ使用步骤
     * 1.创建一个类，使用@Aspect标注
     * 2.@Aspect标注的类中，通过@Pointcut定义切入点
     * 3.@Aspect标注的类中，通过AspectJ提供的一些通知相关的注解定义通知
     * 4.使用AspectJProxyFactory结合@Ascpect标注的类，来生成代理对象
     */

    // execution
    @Test
    public void test1() {
        try {
            //对应目标对象
            Service1 target = new Service1();
            //创建AspectJProxyFactory对象
            AspectJProxyFactory proxyFactory = new AspectJProxyFactory();
            //设置被代理的目标对象
            proxyFactory.setTarget(target);
            //设置标注了@Aspect注解的类
            proxyFactory.addAspect(Aspect1.class);

            //生成代理对象
            Service1 proxy = proxyFactory.getProxy();
            //使用代理对象
            proxy.m1();
            proxy.m2();
        } catch (Exception e) {
        }
    }


    // within
    @Test
    public void test2() {
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory();
        proxyFactory.setTarget(new C2());
        proxyFactory.addAspect(Aspect2.class);

        C2 c2 = proxyFactory.getProxy();
        c2.m1();
        c2.m2();
        c2.m3();
    }

    // this
    @Test
    public void test3() {
        C3 target = new C3();
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAspect(Aspect3.class);
        //设置需要代理的接口
        proxyFactory.setInterfaces(ClassUtils.getAllInterfaces(target));

        //原因：this表达式要求代理对象必须是Service3类型的，输出中可以看出代理对象并不是Service3类型的，此处代理对象proxy是使用jdk动态代理生成的。
        proxyFactory.setProxyTargetClass(false);

        // 使用cglib来创建代理, 增加将会起作用
        // proxyFactory.setProxyTargetClass(true);

        Object c2 = proxyFactory.getProxy();
        ((s30_aop.s07_AspectJ.a3.C1) c2).m1();

    }


    // target
    @Test
    public void test4() {
        C3 target = new C3();
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAspect(Aspect4.class);

        Object obj = proxyFactory.getProxy();
        ((s30_aop.s07_AspectJ.a3.C1) obj).m1();

        System.out.println(C3.class.isAssignableFrom(target.getClass()));
    }

}
