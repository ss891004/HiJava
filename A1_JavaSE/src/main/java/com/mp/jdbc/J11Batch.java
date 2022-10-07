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
public class J11Batch {

    public static void main(String[] args) throws SQLException {
        long starttime = System.currentTimeMillis();

        Connection connection = null;
        PreparedStatement st = null;
        ResultSet resultSet = null;


        try {
            connection = J02.getConnection();

            //1.设置为不自动提交数据
            connection.setAutoCommit(false);

            String sql = "insert into testbatch(id,name) values(?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            for(int i = 1;i <= 1000000;i++){
                ps.setString(1, "name_" + i);
                //1.“攒”sql
                ps.addBatch();
                if(i % 500 == 0){
                    //2.执行
                    ps.executeBatch();
                    //3.清空
                    ps.clearBatch();
                }
            }
            //2.提交数据, 成功后一次性提交
            connection.commit();


        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();

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
