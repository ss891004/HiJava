package com.hm.socket.BIO.b06;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerRunnableTarget implements Runnable {
    private Socket socket;
    public ServerRunnableTarget(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        //处理接收到的客户端socket通信需求
        try {
            //1.从socket管道中得到一个字节输入流对象
            InputStream is = socket.getInputStream();
            //2.把字节输入流包装成一个缓存字符输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String msg;
            while ((msg = br.readLine()) != null) {
                System.out.println("服务端收到：" + msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}