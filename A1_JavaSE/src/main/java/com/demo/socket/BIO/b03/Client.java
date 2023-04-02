package com.demo.socket.BIO.b03;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 * 客户端
 * 在通信中，服务端会一直等待客户端的消息，如果客户端没有进行消息的发送，服务端将一直进入阻塞状态同时服务端是按照行获取消息的，这意味育客户端也必须按照行进行消息的发送，否则服务端将进入等待消息的阻塞状态！
 */
public class Client {
    public static void main(String[] args) {
        try {
            //1.创建Socket对象请求服务端的连接
            Socket socket = new Socket("127.0.0.1",9999);
            //2.从Socket对象中获取一个字节输出流
            OutputStream os = socket.getOutputStream();

            //3.把字节输出流包装成一个打印流
            PrintStream ps = new PrintStream(os);
            ps.println("hello World! 服务端，你好");
            ps.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}