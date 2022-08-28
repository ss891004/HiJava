package com.hm.socket.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        //1.获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",9999));
        //2.切换为非阻塞模式
        sChannel.configureBlocking(false);
        //3.分配指定缓存区大小
        ByteBuffer buf = ByteBuffer.allocate(1024);
        //4.发送数据给服务端
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("请输入：");
            String msg = sc.nextLine();
            buf.put(("波仔：" + msg).getBytes());
            buf.flip();
            sChannel.write(buf);
            buf.clear();
        }
    }


}
