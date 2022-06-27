package com.hm.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 处理MySQL二进制数据(例如图像、声音、二进制文)
/*
 create table mysql_blob
 (
      id int primary key auto_increment,
      image longblob
 );
* */

public class HiMysqlBlob {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "insert into mysql_blob(image) values(?)";
            st = conn.prepareStatement(sql);
            //这种方式获取的路径，其中的空格会被使用“%20”代替
            String path = HiMysqlBlob.class.getClassLoader().getResource("01.jpg").getPath();
            //将“%20”替换会空格
            path = path.replaceAll("%20", " ");
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);//生成的流
            st.setBinaryStream(1, fis, (int) file.length());
            int num = st.executeUpdate();
            if (num > 0) {
                System.out.println("插入成功！！");
            }
            fis.close();


            //////////////////////////////////////////////////

            conn = JdbcUtils.getConnection();
            String sql2 = "select image from mysql_blob where id=?";
            st = conn.prepareStatement(sql2);
            st.setInt(1, 1);
            rs = st.executeQuery();
            if (rs.next()) {
                //InputStream in = rs.getBlob("image").getBinaryStream();//这种方法也可以
                InputStream in = rs.getBinaryStream("image");
                int len = 0;
                byte[] buffer = new byte[1024];

                FileOutputStream out = new FileOutputStream("D:\\1.jpg");
                while ((len = in.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
                in.close();
                out.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}