package com.hm.io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class TestFile {

    public static void main(String[] args) throws IOException {

        // 使用相对路径
        File f1 = new File("dir");
        f1.createNewFile();// 创建一个.txt这个文件
        f1.mkdir();// 创建一个名为.txt的目录

        //使用绝对路径
        File f2 = new File("D:\\dir\\src\\A.java");
        f2.createNewFile();


        //根据不同操作系统获得对应的分隔符 File fDir=new File(File.separator);

        String strFile = "dir" + File.separator + "src" + File.separator + "A.java";
        File f3 = new File(strFile, strFile);
        f3.createNewFile();
        f3.delete();//删除文件或目录
        //f.deleteOnExit();


        /*
         * 在缺省的临时文件目录下创建临时文件
         *
         * for(int i=0;i<5;i++)
         *
         * {
         *
         * File f=File.createTempFile("winTemp",".tmp");
         *
         * f.deleteOnExit();//退出时删除
         *
         *
         *
         * }
         */

        /*
         * 列出指定目录下所有子目录及文件的名称
         */
        File fDir = new File(File.separator);
        String strFile1 = "dir" + File.separator + "src";
        File f4 = new File(fDir, strFile1);
        String[] names = f4.list();
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i]);
        }

        // 有过滤器的情况FilenameFilter是个接口
        File dir = new File(File.separator);

        String filepath = "dir" + File.separator + "src";

        /**
         * dir
         * 上级抽象路径，如果dir为null，那么程序将自动调用单个参数的File构造方法，同时将filepath路径应用到File但构造参数
         * 如果dir为//，则此路径为本文件所在磁盘根目录
         */
        File f5 = new File(dir, filepath);
        if (f5.exists()) {
        } else {
            f5.mkdirs();
        }

        String[] names2 = f5.list(new FilenameFilter() { // 实现了FilenameFilter接口的匿名类，实现accept方法过滤文件

            @Override
            public boolean accept(File dir, String name) {
                System.out.println(name.indexOf(".java"));
                return name.indexOf(".java") != -1;
            }
        });

        for (int i = 0; i < names2.length; i++) {
            System.out.println(names2[i]);
        }
    }


}
