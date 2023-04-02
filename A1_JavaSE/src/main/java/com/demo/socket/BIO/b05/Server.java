package com.demo.socket.BIO.b05;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * 目标：服务端可以实现同时接收多个客户端的Socket通信需求
 * 思路：服务端没接收到一个客户端socket请求对象之后都交给一个独立的线程来处理客户端的数据交互需求
 * 总结：
 * 每个Socket接收到，都会创建一个线程，线程的竞争、切换上下文影响性能
 * 每个线程都会占用栈空间和CPU资源
 * 并不是每个socket都进行lO操作，无意义的线程处理
 * 客户端的并发访问增加时。服务端将呈现1:1的线程开销，访问量越大，系统将发生线程栈溢出
 * 线程创建失败，最终导致进程宕机或者僵死，从而不能对外提供服务
 */
public class Server {
    public static void main(String[] args) {
        try {
            //1.注册端口
            ServerSocket ss = new ServerSocket(9999);
            //2.定义一个死循环，负责不断的接收客户端的Socket的连接请求
            while(true){
                Socket socket = ss.accept();
                //3.创建一个独立的线程来处理与这个客户端的socket通信需求
                new ServerThreadReader(socket).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
