package com.mp.socket.BIO.b08;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
public class ClientReaderThread extends Thread {
    private Socket socket;
    public ClientReaderThread(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            while (true) {
                InputStream is = socket.getInputStream();
                //4.把字节输入流包装成一个缓存字符输入流
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String msg;
                if ((msg = br.readLine()) != null) {
                    System.out.println(msg);
                }
            }
        } catch (Exception e) {
        }
    }
}
