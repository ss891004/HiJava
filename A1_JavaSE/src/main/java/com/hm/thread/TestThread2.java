package com.hm.thread;

/*线程创建与启动的第二种方法：定义Thread的子类并实现run()方法*/
public class TestThread2 {
    public static void main(String args[]) {
        Runner2 r2 = new Runner2();
        r2.start();//调用start()方法启动新开辟的线程
        for (int i = 0; i <= 10; i++) {
            System.out.println("mainMethod：" + i);
        }
    }
}

/*Runner2类从Thread类继承
通过实例化Runner2类的一个对象就可以开辟一个新的线程
调用从Thread类继承来的start()方法就可以启动新开辟的线程*/
class Runner2 extends Thread {
    public void run() {//重写run()方法的实现
        for (int i = 0; i <= 10; i++) {
            System.out.println("Runner2：" + i);
        }
    }
}
