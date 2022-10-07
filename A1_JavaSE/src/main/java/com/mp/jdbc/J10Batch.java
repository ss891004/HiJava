package com.mp.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
  create table testbatch
 (
      id int primary key,
      name varchar(20)
 );

* */

//PreparedStatement 批处理的使用
public class J10Batch {

    public static void main(String[] args) {
        long starttime = System.currentTimeMillis();

        Connection connection = null;
        PreparedStatement st = null;
        ResultSet resultSet = null;


        try {
            connection = J02.getConnection();
            String sql = "insert into testbatch(id,name) values(?,?)";
            st = connection.prepareStatement(sql);
            //多条sql执行的话，只能最多执行1000条。
            for (int i = 1; i <= 1008; i++) {
                st.setInt(1, i);
                st.setString(2, "aa" + i);
                st.addBatch();
                if (i % 400 == 0) {
                    st.executeBatch();
                    st.clearBatch();
                }
            }
            st.executeBatch();


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

        long endtime = System.currentTimeMillis();
        System.out.println("程序花费时间：" + (endtime - starttime) / 1000 + "秒！！");

    }
}
