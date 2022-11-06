package hm.s04_jdkaop;

public  class Service implements IService1,IService2 {
    @Override
    public void m1() {
        System.out.println("IService1~~~m1");

    }

    @Override
    public void m2() {
        System.out.println("IService2~~~m2");

    }
}
