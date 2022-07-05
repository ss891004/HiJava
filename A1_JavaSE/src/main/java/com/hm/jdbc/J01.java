package com.hm.jdbc;

import java.sql.*;

/**
 * JDBC 示例代码
 */

/**
 create database hijdbc default charset =utf8;

 use hijdbc;

 create table users(
 id int primary key auto_increment,
 name varchar(40),
 password varchar(40),
 email varchar(60),
 birthday date
 );

 insert into users(id,name,password,email,birthday) values(1,'zhansan','123456','zs@sina.com','1980-12-04');
 insert into users(id,name,password,email,birthday) values(2,'lisi','123456','lisi@sina.com','1981-12-04');
 insert into users(id,name,password,email,birthday) values(3,'wangwu','123456','wangwu@sina.com','1979-12-04');
 */
public class J01 {

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            //加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            //通过驱动管理类获取数据库链接
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/flask16", "root", "123456");

            //定义sql语句 ?表示占位符
            String sql = "select id,name,password,email,birthday from users where id =?";

            //获取预处理statement
            preparedStatement = connection.prepareStatement(sql);

            //设置参数，第一个参数为sql语句中参数的序号（从1开始），第二个参数为设置的参数值
            preparedStatement.setInt(1, 1);

            //向数据库发出sql执行查询，查询出结果集
            resultSet = preparedStatement.executeQuery();

            //遍历查询结果集
            while (resultSet.next()) {
                System.out.println("id=" + resultSet.getObject("id"));
                System.out.println("name=" + resultSet.getObject("name"));
                System.out.println("password=" + resultSet.getObject("password"));
                System.out.println("email=" + resultSet.getObject("email"));
                System.out.println("birthday=" + resultSet.getObject("birthday"));
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
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
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