package s30_aop.s03_cglib;

public class Service2 {
    public void m1() {
        System.out.println("我是m1方法");
        this.m2(); //@1
    }
    public void m2() {
        System.out.println("我是m2方法");
        this.m3();
    }

    public void m3() {
        System.out.println("我是m3方法");
    }
}
