package com.hm.socket;

import org.junit.Test;

import java.io.*;
import java.net.Socket;

public class socket01 {

    @Test
    public void createServerSocket() {
        try {
            // 初始化服务端socket并且绑定9999端口
            java.net.ServerSocket serverSocket = new java.net.ServerSocket(9999);
            //等待客户端的连接
            Socket socket = serverSocket.accept();
            //获取输入流
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //读取一行数据
            String str = bufferedReader.readLine();

            //输出打印
            System.out.println(str);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createClient() {
        try {
            Socket socket = new Socket("127.0.0.1", 9999);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String str = "你好，这是我的第一个socket";
            bufferedWriter.write(str);
            //刷新输入流
            bufferedWriter.flush();
            //关闭socket的输出流
            socket.shutdownOutput();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
