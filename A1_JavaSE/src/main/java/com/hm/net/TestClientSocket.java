package com.hm.net;

import java.net.*;
import java.io.*;
public class TestClientSocket{
    public static void main(String args[]) throws Exception{
        Socket s = new Socket("127.0.0.1",6666);
        /*Client申请连接到Server端上*/
        /*连接上服务器端以后，就可以向服务器端输出信息和接收从服务器端返回的信息
        输出信息和接收返回信息都要使用流式的输入输出原理进行信息的处理*/
        /*这里是使用输出流OutputStream向服务器端输出信息*/
        OutputStream os = s.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        Thread.sleep(30000);/*客户端睡眠30秒后再向服务器端发送信息*/
        dos.writeUTF("Hello Server!");
    }
}