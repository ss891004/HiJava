package com.demo;

import org.junit.Test;
import java.io.*;

public class TestText {

    /**
     * 分隔符
     * Linux上的文件
     * 以 \001 作为分隔符时，下载后用notePad++打开时看到的SOH，
     * 以 \002 作为分隔符时，下载后用notePad++打开时看到的STX，
     * 以 \003 作为分隔符时，下载后用notePad++打开时看到的ETX.
     *
     * ASCII一共规定了128个字符的编码，占用一个字节的后7位，最前面的1位统一规定为0。比如空格"SPACE"是32，即00100000.
     * UTF-8  是一种变长的编码方式。它可以使用1~4个字节表示一个符号，根据不同的符号而变化字节长度。
     *        如果一个字节的第一位是0，则这个字节单独就是一个字符；如果第一位是1，则连续有多少个1，就表示当前字符占用多少个字节
     * UTF-16
     * UTF-32
     */

    @Test
    public void test(){
        char x = '\001';

      String file="/Users/shuaishi/a.txt";

        try(  FileOutputStream fos= new FileOutputStream(file)){

            for (int i = 0; i < 10; i++) {
                String line ="abbb"+x+"bbbb"+x+"ccccc";
                fos.write(line.getBytes());
                fos.write("\n".getBytes());
            }


        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

