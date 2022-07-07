import hm.s01_proxy.IService;
import hm.s01_proxy.ServiceA;
import hm.s01_proxy.ServiceB;
import hm.s01_proxy.ServiceProxy;
import org.junit.Test;

public class S01 {
    @Test
    public void test1() {
        IService serviceA = new ServiceA();
        IService serviceB = new ServiceB();
        serviceA.m1();
        serviceA.m2();
        serviceA.m3();
        serviceB.m1();
        serviceB.m2();
        serviceB.m3();
    }
    //调用IService接口中的任何方法的时候，需要记录方法的耗时。
    //调用IService接口中的任何方法的时候，需要记录方法的日志。等等

    // ServiceProxy是IService接口的代理类，target为被代理的对象，即实际需要访问的对象，也实现了IService接口，
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
