package hm.s01_proxy;

// ServiceProxy是IService接口的代理类，target为被代理的对象，即实际需要访问的对象，也实现了IService接口，
public class ServiceProxy implements IService {
    //为IService接口创建一个代理类，通过这个代理类来间接访问IService接口的实现类，在这个代理类中去做耗时及发送至监控的代码
    //目标对象，被代理的对象
    private final IService target;
    public ServiceProxy(IService target) {
        this.target = target;
    }
    @Override
    public void m1() {
        long starTime = System.nanoTime();
        this.target.m1();
        long endTime = System.nanoTime();
        System.out.println(this.target.getClass() + ".m1()方法耗时(纳秒):" + (endTime - starTime));
    }
    @Override
    public void m2() {
        long starTime = System.nanoTime();
        this.target.m1();
        long endTime = System.nanoTime();
        System.out.println(this.target.getClass() + ".m1()方法耗时(纳秒):" + (endTime - starTime));
    }
    @Override
    public void m3() {
        long starTime = System.nanoTime();
        this.target.m1();
        long endTime = System.nanoTime();
        System.out.println(this.target.getClass() + ".m1()方法耗时(纳秒):" + (endTime - starTime));
    }
}

