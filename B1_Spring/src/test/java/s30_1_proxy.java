import s30_aop.s01_proxy.IService;
import s30_aop.s01_proxy.ServiceA;
import s30_aop.s01_proxy.ServiceB;
import s30_aop.s01_proxy.ServiceProxy;
import org.junit.Test;

// 使用代理
public class s30_1_proxy {
    @Test
    public void test1() {
        // 最普通的方法调用
        IService serviceA = new ServiceA();
        IService serviceB = new ServiceB();
        serviceA.m1();
        serviceA.m2();
        serviceA.m3();
        serviceB.m1();
        serviceB.m2();
        serviceB.m3();
    }


    // 调用IService接口中的任何方法的时候，需要记录方法的耗时。
    // 调用IService接口中的任何方法的时候，需要记录方法的日志。
    // 解决办法：可以为IService接口创建一个代理类，通过这个代理类来间接访问IService接口的实现类，在这个代理类中去做耗时及发送至监控的代码

    // 上面的3个方法中加了统计耗时的代码，当我们需要访问IService的其他实现类的时候，可以通过ServiceProxy来间接的进行访问，
    @Test
    public void serviceProxy() {
        IService serviceA = new ServiceProxy(new ServiceA());//@1
        IService serviceB = new ServiceProxy(new ServiceB()); //@2
        serviceA.m1();
        serviceA.m2();
        serviceA.m3();

        serviceB.m1();
        serviceB.m2();
        serviceB.m3();
    }

}
