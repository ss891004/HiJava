package com.mp.socket;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelTest {

    //本地文件写数据
    @Test
    public void write(){
        try{
            //1.字节输出流通向目标文件
            FileOutputStream fos = new FileOutputStream("data01_txt");
            //2.得到字节输出流对应的通道Channel
            FileChannel channel = fos.getChannel();
            //3.分配缓存区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("hello，java程序员！".getBytes());
            //4.把缓存区切换为写模式
            buffer.flip();
            channel.write(buffer);
            channel.close();
            System.out.println("写数据到文件中！");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //本地文件读数据
    @Test
    public void read() throws Exception {
        //1.定义一个文件字节输入流与源文件接通
        FileInputStream is = new FileInputStream("data01_txt");
        //2.需要得到文件字节输入流的文件通道
        FileChannel channel = is.getChannel();
        //3.定义一个缓存区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //4.读取数据到缓存区
        channel.read(buffer);
        buffer.flip();//归位
        //5.读取出缓存区中的数据并输出即可
        String rs = new String(buffer.array(),0,buffer.remaining());
        System.out.println(rs);
    }

    //使用Buffer完成文件复制
    @Test
    public void copy() throws Exception {
        //源文件
        File srcFile = new File("C:\\Users\\Lenovo\\Desktop\\1.jpg");
        File destFile = new File("C:\\Users\\Lenovo\\Desktop\\server\\1_copy.jpg");
        //得到一个字节输出流、字节输入流
        FileInputStream fis = new FileInputStream(srcFile);
        FileOutputStream fos = new FileOutputStream(destFile);
        //得到文件通道
        FileChannel fisChannel = fis.getChannel();
        FileChannel fosChannel = fos.getChannel();
        //分配缓存区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true){
            //必须先清空缓存区然后再写入数据到缓存区
            buffer.clear();
            //开始读取一次数据
            int flag = fisChannel.read(buffer);
            if(flag == -1){
                break;
            }
            //已经读取了数据，把缓存区的模式切换为可读模式
            buffer.flip();
            //把数据写出到输出通道
            fosChannel.write(buffer);
        }
        fisChannel.close();;
        fosChannel.close();
        System.out.println("复制完成");
    }


    // 分散读取（Scatter)：是指把Channel通道的数据读取入到多个缓存区中去
    // 聚集写入（Gathering)：是指将多个Buffer中的数据聚集到Channel。
    @Test
    public void test() throws Exception {
        //1.字节输入管道
        FileInputStream is = new FileInputStream("data01_txt");
        FileChannel isChannel = is.getChannel();
        //2.字节输出管道
        FileOutputStream os = new FileOutputStream("data02_txt");
        FileChannel osChannel = os.getChannel();
        //3.定义多个缓存区做数据分散
        ByteBuffer buffer1 = ByteBuffer.allocate(4);
        ByteBuffer buffer2 = ByteBuffer.allocate(1024);
        ByteBuffer[] buffers = {buffer1, buffer2};
        //4.从通道中读取数据分散到各个缓存区
        isChannel.read(buffers);
        //5.从每个缓存区中查询是否有数据读取到了
        for (ByteBuffer buffer : buffers) {
            buffer.flip();//切换到读数据模式
            System.out.println(new String(buffer.array(), 0, buffer.remaining()));
        }
        //6.聚集写入到通道
        osChannel.write(buffers);
        isChannel.close();
        osChannel.close();
        System.out.println("文件复制~");
    }

    //从目标通道中去复制原通道数据
    @Test
    public void test02() throws Exception{
        //1.字节输入管道
        FileInputStream is = new FileInputStream("data01_txt");
        FileChannel isChannel = is.getChannel();//原通道
        //2.字节输出管道
        FileOutputStream os = new FileOutputStream("data03_txt");
        FileChannel osChannel = os.getChannel();//目标通道
        //3.复制数据
        osChannel.transferFrom(isChannel,isChannel.position(),isChannel.size());
        isChannel.close();
        osChannel.close();
        System.out.println("复制完成！");
    }

    //把原通道数据复制到目标通道
    @Test
    public void test03() throws Exception{
        //1.字节输入管道
        FileInputStream is = new FileInputStream("data01_txt");
        FileChannel isChannel = is.getChannel();
        //2.字节输出管道
        FileOutputStream os = new FileOutputStream("data04_txt");
        FileChannel osChannel = os.getChannel();
        //3.复制数据
        //osChannel.transferFrom(isChannel,isChannel.position(),isChannel.size());
        isChannel.transferTo(isChannel.position(),isChannel.size(),osChannel);
        isChannel.close();
        osChannel.close();
        System.out.println("复制完成！");
    }
}
