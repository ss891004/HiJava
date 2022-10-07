package com.mp.socket.BIO.b06;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * 目标：开发实现伪异步通讯架构
 * 思路：服务端没接收到一个客户端socket请求对象之后都交给一个独立的线程来处理客户端的数据交互需求
 * 总结：
 * 伪异步旧采用了线程池实现，因此避免了为每个请求创建一个独立线程造成线程资源耗尽的问题，
 * 但由于底层 依然是采用的同步阻塞模型，因此无法从根采上解决问题。
 * 如果单个消息处理的缓慢，或者服务器线程池中的全部线程都被阻塞，那么后续socket的I/O消息
 * 都将在队列 中排队。新的Socket请求将被拒绝，客户端会发生大量连接超时。
 */
public class Server {
    public static void main(String[] args) {
        try {
            //1.注册端口
            ServerSocket ss = new ServerSocket(9999);
            //2.定义一个死循环，负责不断的接收客户端的Socket的连接请求
            //初始化一个线程池对象
            HandlerSocketServerPool pool = new HandlerSocketServerPool(3, 10);
            while (true) {
                Socket socket = ss.accept();
                //3.把socket对象交给一个线程池进行处理
                //把socket封装成一个任务对象交给线程池处理
                Runnable target = new ServerRunnableTarget(socket);
                pool.execute(target);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
