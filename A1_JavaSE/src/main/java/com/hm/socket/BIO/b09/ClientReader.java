package com.hm.socket.BIO.b09;

import java.io.DataInputStream;
import java.net.Socket;
import java.util.Arrays;


/*客户端设计

启动客户端界面，登录，刷新在线

启动客户端界面，登陆，刷新在线人数列表

客户端界面主要是GUI设计，主体页面分为登陆界面和聊 天窗口，以及在线用户列表。

登陆输入服务端ip和用户名后，要请求与服务端的登 陆，然后立即为当前客户端分配一个读线程处理客户端 的读数据消息。因为客户端可能随时会接收到服务端那 边转发过来的各种即时消息信息。 客户端登陆完成，服务端收到登陆的用户名后，会立即发 来最新的用户列表给客户端更新。

客户端发送消息逻辑

目标

客户端发送群聊消息，@消息，以及私聊消息。

实现步骤

客户端启动后，在聊天界面需要通过发送按钮推送群 聊消息，@消息，以及私聊消息。*/


public class ClientReader extends Thread {
    private Socket socket;
    private ClientChat clientChat;
    public ClientReader(ClientChat clientChat, Socket socket) {
        this.clientChat = clientChat;
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            /** 循环一直等待客户端的消息 */
            while (true) {
                /** 读取当前的消息类型： 登录，群发，私聊，@消息 */
                int flag = dis.readInt();
                if (flag == 1) {
                    //在线人数消息回来了
                    String nameDatas = dis.readUTF();
                    System.out.println(nameDatas);
                    //展示到在线人数的界面
                    String[] names = nameDatas.split(Constants.SPILIT);
                    System.out.println(Arrays.toString(names));
                    clientChat.onLineUsers.setListData(names);
                } else if (flag == 2) {
                    //群发，私聊 ，@消息 都是直接显示的
                    String msg = dis.readUTF();
                    clientChat.smsContent.append(msg);
                    //让消息界面滚动到低端
                    clientChat.smsContent.setCaretPosition(clientChat.smsContent.getText().length()
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
