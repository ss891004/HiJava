package com.demo.socket;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * 目标：对缓存区Buffer的常用API进行案例实现
 */
public class BufferTest {
    @Test
    public void test01() {
        //1.分配一个缓存区，容量设置为10
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println(buffer.position());//0
        System.out.println(buffer.limit());//10   返回Buffer的界限（limit）的位置
        System.out.println(buffer.capacity());//10  返回Buffer的capacity大小
        System.out.println("-----------------------");

        String name = "itheima"; //put 往缓存区中添加数据
        buffer.put(name.getBytes());
        System.out.println(buffer.position());//7
        System.out.println(buffer.limit());//10
        System.out.println(buffer.capacity());//10
        System.out.println("-----------------------");

        buffer.flip(); //将缓存区的界限设置为当前位置，并将当前位置设置为 0 可读模式
        System.out.println(buffer.position());//0
        System.out.println(buffer.limit());//7
        System.out.println(buffer.capacity());//10
        System.out.println("-----------------------");

        char ch = (char) buffer.get(); //get数据的读取
        System.out.println(ch);//i
        System.out.println(buffer.position());//1
        System.out.println(buffer.limit());//7
        System.out.println(buffer.capacity());//10
    }


    @Test
    public void test02() {
        //1.分配一个缓存区，容量设置为10
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println(buffer.position());//0
        System.out.println(buffer.limit());//10
        System.out.println(buffer.capacity());//10
        System.out.println("-----------------------");
        String name = "itheima";
        buffer.put(name.getBytes());
        System.out.println(buffer.position());//7
        System.out.println(buffer.limit());//10
        System.out.println(buffer.capacity());//10
        System.out.println("-----------------------");
        //2.clear 清楚缓存区中的数据
        buffer.clear(); //清空缓冲区并返回对缓冲区的引用
        System.out.println(buffer.position());//0
        System.out.println(buffer.limit());//10
        System.out.println(buffer.capacity());//10
        System.out.println((char) buffer.get());//i 表明数据并没有清除 只是恢复了position的位置
        System.out.println("-----------------------");
        //3.定义一个缓存区
        ByteBuffer buf = ByteBuffer.allocate(10);
        String n = "itheima";
        buf.put(n.getBytes());
        buf.flip(); //为将缓冲区的界限设置为当前位置，并将当前位置重置为0
        //读取数据
        byte[] b = new byte[2];
        buf.get(b);
        String rs = new String(b);
        System.out.println(rs);//it
        System.out.println(buf.position());//2
        System.out.println(buf.limit());//7
        System.out.println(buf.capacity());//10
        System.out.println("-----------------------");
        buf.mark();//标记此刻这个位置：2   // 对缓冲区设置标记
        byte[] b2 = new byte[3];
        buf.get(b2);
        System.out.println(new String(b2));//hei
        System.out.println(buf.position());//5
        System.out.println(buf.limit());//7
        System.out.println(buf.capacity());//10
        System.out.println("-----------------------");
        buf.reset();//回到标记位置 2  //将位置position转到以前设置的mark所在的位置
        if (buf.hasRemaining()) {
            System.out.println(buf.remaining());//5
        }
    }



    //直接与非直接缓存区
    @Test
    public void test03(){
        //创建一个非直接内存的缓存区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //buffer.isDirect()用于判断是否为直接内存
        System.out.println(buffer.isDirect());
        System.out.println("----------------");
        //创建一个直接内存的缓存区
        ByteBuffer buffer2 = ByteBuffer.allocateDirect(1024);
        System.out.println(buffer2.isDirect());
    }


}