package s30_aop.s07_AspectJ.a1;


public class Service1 {

    public void m1() {
        System.out.println("我是 m1 方法");
    }
    public void m2() {
        System.out.println(10 / 0);

        System.out.println("我是 m2 方法");
    }
}