package com.mp.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
  create table testbatch
 (
      id int primary key,
      name varchar(20)
 );

* */

//Statement 批处理的使用
public class J09Batch {

    public static void main(String[] args) {
        Connection connection = null;
        Statement st = null;
        ResultSet resultSet = null;

        try {
            connection = J02.getConnection();
            //要执行的SQL命令，SQL中的参数使用?作为占位符
            //String sql = "insert into users(id,name,password,email,birthday) values(?,?,?,?,?)";
            String sql1 = "insert into testbatch(id,name) values(1,'a')";
            String sql2 = "insert into testbatch(id,name) values(2,'b')";
            String sql3 = "insert into testbatch(id,name) values(3,'c')";
            st = connection.createStatement();
            st.addBatch(sql1);
            st.addBatch(sql2);
            st.addBatch(sql3);
            int[] rst = st.executeBatch();
            System.out.println(rst);
            st.clearBatch();

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
            if (st != null) {
                try {
                    st.close();
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
