package com.mp.socket.BIO.b09;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Set;



/*服务端设计

服务端接收多个客户端逻辑

服务端需要接收多个客户端的接入

服务端需要接收多个客户端，目前我们采取的策略是一个 客户端对应一个服务端线程
服务端除了要注册端口以外，还需要为每个客户端分配 一个独立线程处理与之通信
服务端主体代码，主要进行端口注册，和接收客户端，分配线程处理该客户端请求

服务端接收登陆消息以及监测离线

在上面我们实现了服务端可以接收多个客户端，然后服务端可以接收多个客户端连接，接下来我们要接收客户端的登陆消息。

我们需要在服务端处理客户端线程的登陆消息。需要注意的是，服务端需要接收客户端的消息可能有很多种,分别是登陆消息，群聊消息，私聊消息和@消息。这里需要约定如果客户端发送消息之前需要先发送消息的类型，类 型我们使用信号值标志（1，2，3）。

1代表接收的是登陆消息
2代表群发| @消息
3代表了私聊消息
服务端的线程中有异常校验机制，一旦发现客户端下线会在异 常机制中处理，然后移除当前客户端用户，把最新的用户列表 发回给全部客户端进行在线人数更新。

服务端接收群聊消息

在上面实现了接收客户端的登陆消息，然后提取当前在线的全部的用户名称和当前登陆的用户名称，发送给全部在线用户更新自己的在线人数列表。接下来要接收客户端发来的群聊消息，然后推送给当前在线的所有客户端。

服务端接收私聊消息

解决私聊消息的推送逻辑，私聊消息需要知道推送给某个具 体的客户端。我们可以接收到客户端发来的私聊用户名称，根据用户名称定 位该用户的Socket管道，然后单独推送消息给该Socket管道。*/
public class ServerReader extends Thread {
    private Socket socket;
    public ServerReader(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(socket.getInputStream());
            //1.循环一直等待客户端的消息
            while (true) {
                //2.读取当前的消息类型：登录，群发，私聊，@消息
                int flag = dis.readInt();
                if (flag == 1) {
                    //先将当前登录的客户端socket存到在线人数的socket集合中
                    String name = dis.readUTF();
                    System.out.println(name + "----->" +
                            socket.getRemoteSocketAddress());
                    ServerChat.onLineSockets.put(socket, name);
                }
                writeMsg(flag, dis);
            }
        } catch (Exception e) {
            System.out.println("---有人下线了---");
            ServerChat.onLineSockets.remove(socket);
            try {
                //重新更新在线人数并发给所有客户端
                writeMsg(1, dis);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    private void writeMsg(int flag, DataInputStream dis) throws Exception {
        //DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        //定义一个变量存放最终的消息形式
        String msg = null;
        if (flag == 1) {
            //读取所有在线人数发给所有客户端去更新自己的在线人数列表
            StringBuilder rs = new StringBuilder();
            /** onlineNames = [波仔,zhangsan,波妞]*/
            Collection<String> onlineNames = ServerChat.onLineSockets.values();
            //判断是否存在在线人数
            if (onlineNames != null && onlineNames.size() > 0) {
                for (String name : onlineNames) {
                    rs.append(name + Constants.SPILIT);
                }
                //波仔003197♣♣㏘♣④④♣zhangsan003197♣♣㏘♣④④♣波妞003197♣♣㏘♣④④♣
                //去掉最后一个分隔符
                msg = rs.substring(0, rs.lastIndexOf(Constants.SPILIT));
                //将消息发送给所有的客户端
                sendMsgToAll(flag, msg);
            }
        } else if (flag == 2 || flag == 3) {
            //读到消息 群发的 或则 @消息
            String newMsg = dis.readUTF();//消息
            //得到发件人
            String sendName = ServerChat.onLineSockets.get(socket);
            // 内容--
            StringBuilder msgFinal = new StringBuilder();
            //时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEE");
            if (flag == 2) {//群发消息 和 @消息
                msgFinal.append(sendName).append("").append(sdf.format(System.currentTimeMillis() * 2)).append("\r\n");
                msgFinal.append(" ").append(newMsg).append("\r\n");
                sendMsgToAll(flag, msgFinal.toString());
            } else if (flag == 3) {//私聊消息
                msgFinal.append(sendName).append("").append(sdf.format(System.currentTimeMillis() * 2)).append(" 对您私发\r\n");
                msgFinal.append(" ").append(newMsg).append("\r\n");
                //私发
                //得到发给谁
                String destName = dis.readUTF();
                sendMsgToOne(destName, msgFinal.toString());
            }
        }
    }
    /**
     * @param destName 对谁私发
     * @param msg      发的消息内容
     * @throws Exception
     */
    private void sendMsgToOne(String destName, String msg) throws Exception {
        //拿到所有在线的socket管道 给这些管道写出消息
        Set<Socket> allOnLineSockets = ServerChat.onLineSockets.keySet();
        for (Socket sk : allOnLineSockets) {
            //得到当前需要私发的Socket
            //只对这个名字对应的socket私发消息
            if (ServerChat.onLineSockets.get(sk).trim().equals(destName)) {
                DataOutputStream dos = new
                        DataOutputStream(sk.getOutputStream());
                dos.writeInt(2);//消息类型
                dos.writeUTF(msg);
                dos.flush();
            }
        }
    }
    private void sendMsgToAll(int flag, String msg) throws Exception {
        //拿到所有的在线socket管道 给这些管道写出消息
        Set<Socket> allOnLineSockets = ServerChat.onLineSockets.keySet();
        for (Socket sk : allOnLineSockets) {
            DataOutputStream dos = new DataOutputStream(sk.getOutputStream());
            dos.writeInt(flag);//消息类型
            dos.writeUTF(msg);
            dos.flush();
        }
    }
}
