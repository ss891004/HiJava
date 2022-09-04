package com.hm.jdbc;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.sql.*;

// 处理MySQL大文本数据
/*
create table mysql_clob
( id int primary key auto_increment,
 resume text
);
* */

public class J08Text {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        Statement statement = null;
        Reader reader = null;
        try {
            connection = J02.getConnection();
            String sql = "insert into mysql_clob(resume) values(?)";
            pst = connection.prepareStatement(sql);
            //这种方式获取的路径，其中的空格会被使用“%20”代替
            //String path = HiMysqlClob.class.getClassLoader().getResource("a.txt").getPath();
            //System.out.println(path);
            //将“%20”替换回空格
            //path = path.replaceAll("%20", " ");
            File file = new File("/home/shishuai/IdeaProjects/HiJava/HiJDBC/src/main/java/com/hmrcb/a.txt");
            reader = new FileReader(file);
            pst.setCharacterStream(1, reader, (int) file.length());
            int num = pst.executeUpdate();
            if (num > 0) {
                System.out.println("插入成功！！");
            }
            //关闭流
            reader.close();


            //////////////////////////////////////////////////

            connection = J02.getConnection();
            String sql2 = "select resume from mysql_clob where id=2";
            pst = connection.prepareStatement(sql2);
            resultSet = pst.executeQuery();

            String contentStr = "";
            String content = "";
            if (resultSet.next()) {
                //使用resultSet.getString("字段名")获取大文本数据的内容
                content = resultSet.getString("resume");
                //使用resultSet.getCharacterStream("字段名")获取大文本数据的内容
                Reader reader2 = resultSet.getCharacterStream("resume");
                char[] buffer = new char[1024];
                int len = 0;
                FileWriter out = new FileWriter("1.txt");
                while ((len = reader2.read(buffer)) > 0) {
                    contentStr += new String(buffer);
                    out.write(buffer, 0, len);
                }
                out.close();
                reader2.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}