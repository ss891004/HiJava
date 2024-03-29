package com.demo.jdbc;

import java.io.*;
import java.text.MessageFormat;
import java.util.Properties;


//读取properties文件
public class PropertiesFileReadTest {

    public static void main(String[] args) {
        try {
            readPropFileByGetResourceAsStream();
            System.out.println();
            readPropFileByInPutStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用getResourceAsStream方法读取properties文件
     */
    static void readPropFileByGetResourceAsStream() {
        /**
         * 读取src下面config.properties包内的配置文件
         * test1.properties位于config.properties包内
         */
        InputStream in1 = PropertiesFileReadTest.class.getClassLoader().getResourceAsStream("com.hm.config/properties/test1.properties");
        /**
         * 读取和PropertiesFileReadTest类位于同一个包里面的配置文件
         * test2.properties和PropertiesFileReadTest类在同一个包里面
         */
        InputStream in2 = PropertiesFileReadTest.class.getResourceAsStream("test2.properties");
        /**
         * 读取src根目录下文件的配置文件
         * jdbc.properties位于src目录
         */
        InputStream in3 = PropertiesFileReadTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        /**
         * 读取位于另一个source文件夹里面的配置文件
         * config是一个source文件夹，com.hm.config.properties位于config source文件夹中
         */
        InputStream in4 = PropertiesFileReadTest.class.getClassLoader().getResourceAsStream("com.hm.config.properties");

        Properties p = new Properties();
        System.out.println("----使用getResourceAsStream方法读取properties文件----");
        try {
            System.out.println("----------------------------------------------");
            p.load(in1);
            System.out.println("test1.properties:name=" + p.getProperty("name") + ",age=" + p.getProperty("age"));
            System.out.println("----------------------------------------------");

            p.load(in2);
            System.out.println("test2.properties:name=" + p.getProperty("name") + ",age=" + p.getProperty("age"));
            System.out.println("----------------------------------------------");

            p.load(in3);
            System.out.println("jdbc.properties:");
            System.out.println(String.format("jdbc.driver=%s", p.getProperty("jdbc.driver")));// 这里的%s是java String占位符
            System.out.println(String.format("jdbc.url=%s", p.getProperty("jdbc.url")));
            System.out.println(String.format("jdbc.usename=%s", p.getProperty("jdbc.usename")));
            System.out.println(String.format("jdbc.password=%s", p.getProperty("jdbc.password")));
            System.out.println("----------------------------------------------");

            p.load(in4);
            System.out.println("com.hm.config.properties:");
            System.out.println(MessageFormat.format("dbuser={0}", p.getProperty("dbuser")));// {0}是一个java的字符串占位符
            System.out.println(MessageFormat.format("dbpassword={0}", p.getProperty("dbpassword")));
            System.out.println(MessageFormat.format("database={0}", p.getProperty("database")));
            System.out.println("----------------------------------------------");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (in1 != null) {
                try {
                    in1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (in2 != null) {
                try {
                    in2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (in3 != null) {
                try {
                    in3.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (in4 != null) {
                try {
                    in4.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 使用InPutStream流读取properties文件
     */
    static void readPropFileByInPutStream() {
        InputStream in1 = null;
        InputStream in2 = null;
        InputStream in3 = null;
        InputStream in4 = null;
        System.out.println("----使用InputStream流读取properties文件----");
        try {
            /**
             * 读取src根目录下文件的配置文件
             * jdbc.properties位于src目录
             */
            in1 = new BufferedInputStream(new FileInputStream(
                    "src/jdbc.properties"));
            /**
             * 读取src下面config.properties包内的配置文件
             * test1.properties位于config.properties包内
             */
            in2 = new BufferedInputStream(new FileInputStream(
                    "src/com.hm.config/properties/test1.properties"));
            /**
             * 读取和PropertiesFileReadTest类位于同一个包里面的配置文件
             * test2.properties和PropertiesFileReadTest类在同一个包里面
             */
            in3 = new BufferedInputStream(new FileInputStream(
                    "src/propertiesFile/read/test/test2.properties"));
            /**
             * 读取位于另一个source文件夹里面的配置文件
             * config是一个source文件夹，com.hm.config.properties位于config source文件夹中
             */
            in4 = new FileInputStream("com.hm.config/com.hm.config.properties");

            Properties p = new Properties();
            System.out
                    .println("----------------------------------------------");

            p.load(in1);
            System.out.println("jdbc.properties:");
            System.out.println(String.format("jdbc.driver=%s",
                    p.getProperty("jdbc.driver")));// 这里的%s是java String占位符
            System.out.println(String.format("jdbc.url=%s",
                    p.getProperty("jdbc.url")));
            System.out.println(String.format("jdbc.usename=%s",
                    p.getProperty("jdbc.usename")));
            System.out.println(String.format("jdbc.password=%s",
                    p.getProperty("jdbc.password")));
            System.out
                    .println("----------------------------------------------");

            p.load(in2);
            System.out.println("test1.properties:name=" + p.getProperty("name")
                    + ",age=" + p.getProperty("age"));
            System.out
                    .println("----------------------------------------------");
            p.load(in3);
            System.out.println("test2.properties:name=" + p.getProperty("name")
                    + ",age=" + p.getProperty("age"));
            System.out
                    .println("----------------------------------------------");

            p.load(in4);
            System.out.println("com.hm.config.properties:");
            System.out.println(MessageFormat.format("dbuser={0}",
                    p.getProperty("dbuser")));// {0}是一个java的字符串占位符
            System.out.println(MessageFormat.format("dbpassword={0}",
                    p.getProperty("dbpassword")));
            System.out.println(MessageFormat.format("database={0}",
                    p.getProperty("database")));
            System.out
                    .println("----------------------------------------------");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in1 != null) {
                try {
                    in1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (in2 != null) {
                try {
                    in2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (in3 != null) {
                try {
                    in3.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (in4 != null) {
                try {
                    in4.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}