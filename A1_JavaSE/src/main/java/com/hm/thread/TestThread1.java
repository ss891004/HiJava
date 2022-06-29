package com.hm.thread;

/*
进程：进程是一个静态的概念
线程：一个进程里面有一个主线程叫main()方法，是一个程序里面的，一个进程里面不同的执行路径。
在同一个时间点上，一个CPU只能支持一个线程在执行。因为CPU运行的速度很快，因此我们看起来的感觉就像是多线程一样。
*/

public class TestThread1 {
    public static void main(String args[]) {
        Runner1 r1 = new Runner1();//这里new了一个线程类的对象出来
        //r1.run();//这个称为方法调用，方法调用的执行是等run()方法执行完之后才会继续执行main()方法
        Thread t = new Thread(r1);//要启动一个新的线程就必须new一个Thread对象出来
        //这里使用的是Thread(Runnable target) 这构造方法
        t.start();//启动新开辟的线程，新线程执行的是run()方法，新线程与主线程会一起并行执行
        for (int i = 0; i < 10; i++) {
            System.out.println("maintheod：" + i);
        }
    }
}
/*定义一个类用来实现Runnable接口，实现Runnable接口就表示这个类是一个线程类*/
class Runner1 implements Runnable {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Runner1：" + i);
        }
    }
}