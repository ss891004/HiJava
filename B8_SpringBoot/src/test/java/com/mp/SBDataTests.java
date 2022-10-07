package com.mp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

// 整合JDBC
@RunWith(SpringRunner.class)
@SpringBootTest
public class SBDataTests {
    @Autowired
    DataSource datasource;  // 当只有一个数据库时，可以自动装配

    @Test
    public void contextLoads() throws SQLException {
        // 默认采用的数据源连接池：com.zaxxer.hikari.HikariDataSource
        System.out.println("datasource: " + datasource.getClass());
        Connection connection = datasource.getConnection();
        System.out.println(connection);
        connection.close();
    }
}