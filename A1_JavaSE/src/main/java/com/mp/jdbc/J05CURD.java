package com.mp.jdbc;

import java.sql.*;

//PreparedStatement 的使用
public class J05CURD {

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet resultSet = null;

        try {
            //获取一个数据库连接
            connection = J02.getConnection();
            //要执行的SQL命令，SQL中的参数使用?作为占位符
            //String sql = "insert into users(id,name,password,email,birthday) values(?,?,?,?,?)";
            String sql = "insert into users(name,password,email,birthday) values(?,?,?,?)";
            //通过conn对象获取负责执行SQL命令的prepareStatement对象
            pst = connection.prepareStatement(sql);
            //为SQL语句中的参数赋值，注意，索引是从1开始的
            /**
             * SQL语句中各个字段的类型如下：
             *  +----------+-------------+
             | Field    | Type        |
             +----------+-------------+
             | id       | int(11)     |
             | name     | varchar(40) |
             | password | varchar(40) |
             | email    | varchar(60) |
             | birthday | date        |
             +----------+-------------+
             */
            //pst.setInt(1, 1);//id是int类型的
            pst.setString(1, "白虎神皇");//name是varchar(字符串类型)
            pst.setString(2, "123");//password是varchar(字符串类型)
            pst.setString(3, "bhsh@sina.com");//email是varchar(字符串类型)
            //pst.setDate(5, new java.sql.Date(new Date().getTime()));//birthday是date类型
            pst.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            //执行插入操作，executeUpdate方法返回成功的条数
            int num = pst.executeUpdate();
            if (num > 0) {
                System.out.println("插入成功！！");
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
