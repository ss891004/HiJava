package com.mp.socket.BIO.b07;

import java.net.ServerSocket;
import java.net.Socket;
/**
 * 目标：服务端开发，可以实现接收客户端的任意类型文件，并保存到服务器端磁盘
 *
 * 同步阻塞模式下（BIO)，客户端怎么发，服务端就必须对应的怎么收。如客户端用的是DataOutputStream，那么服务端就该用DataInputStream，客户端dos.writeUTF(“.jpg”);服务端就该String suffix = dis.readUTF();
 * 客户端发完数据后必须通知服务端自己已经发完socket.shutdownOutput()，否则服务端会一直等待
 */
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(8888);
            while (true) {
                Socket socket = ss.accept();
                //交给一个独立的线程来处理与这个客户端的文件通信需求
                new ServerReadThread(socket).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
