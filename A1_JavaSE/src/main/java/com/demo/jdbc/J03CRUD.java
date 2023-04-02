package com.demo.jdbc;

import java.sql.*;

// curd 的使用
public class J03CRUD {

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Statement  statement = null;

        try {
            //加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            //通过驱动管理类获取数据库链接
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hijdbc", "root", "123456");

            statement =   connection.createStatement();

            //插入数据
            String insrtStr=" insert into users(name,password,email,birthday) values('zhansan','123456','zs@sina.com','1980-12-04')";
            statement.executeUpdate(insrtStr);

            //查询数据
            String selectStr="select id,name,password,email,birthday from users";
            resultSet=  statement.executeQuery(selectStr);
            System.out.print(resultSet.hashCode());

            //删除数据
            String deleteStr=" delete from users where id <= 3";
            statement.executeUpdate(deleteStr);

            //更新数据
            String updateStr="update users set name ='xxxxx' where id = 10 ";
            statement.executeUpdate(updateStr);


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
