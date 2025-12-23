package com.mybatis;

import com.mybatis.mapper.UserMapper6;
import com.mybatis.model.UserModel2;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.UUID;

//增删改知识点汇总及主键获取3种方式详解
@Slf4j
public class Test04 {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws IOException {
        //指定mybatis全局配置文件
        String resource = "src/main/resources/mybatis-config.xml";
        //读取全局配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //构建SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        this.sqlSessionFactory = sqlSessionFactory;
    }

    // 建库建表
    // mybatis增删改返回值说明及源码解析
    // jdbc获取自增值的3种方式详解
    // mybatis获取自增值的3种方式详解

    @Test
    public  void insertUser()
    {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            UserMapper6 mapper = sqlSession.getMapper(UserMapper6.class);
            UserModel2 um = UserModel2.builder().id(1L).name("路人甲Java").age(30).salary(50000D).sex(1).build();
         int a=   mapper.insertUser(um);
            System.out.println(a);
        }
    }

    private final String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    private final String jdbcUrl = "jdbc:mysql://localhost:3306/mybatis?characterEncoding=UTF-8";
    private final String jdbcUserName = "root";
    private final String jdbcPassword = "123456";
    @Test
    public void jdbcInsertUser1() throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            UserModel2 userModel = UserModel2.builder().name("黎明").age(30).salary(50000D).sex(1).build();
            //执行jdbc插入数据操作
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(jdbcUrl, jdbcUserName, jdbcPassword);
            //注意创建PreparedStatement的时候，使用prepareStatement方法的第二个参数需要指定Statement.RETURN_GENERATED_KEYS
            preparedStatement = connection.prepareStatement("INSERT INTO t_user2 (name,age,salary,sex) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            int parameterIndex = 1;
            preparedStatement.setString(parameterIndex++, userModel.getName());
            preparedStatement.setInt(parameterIndex++, userModel.getAge());
            preparedStatement.setDouble(parameterIndex++, userModel.getSalary());
            preparedStatement.setInt(parameterIndex++, userModel.getSex());
            int count = preparedStatement.executeUpdate();
            log.info("影响行数：{}", count);
            //获取自增值
            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys != null && generatedKeys.next()) {
                log.info("自增值为：{}", generatedKeys.getInt(1));
            }
        } finally {
            if (generatedKeys != null && generatedKeys.isClosed()) {
                generatedKeys.close();
            }
            if (preparedStatement != null && preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            if (connection != null && connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Test
    public void jdbcInsertUser2() throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            UserModel2 userModel = UserModel2.builder().name("梁朝伟").age(30).salary(50000D).sex(1).build();
            //执行jdbc插入数据操作
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(jdbcUrl, jdbcUserName, jdbcPassword);
            //注意创建PreparedStatement的时候，使用prepareStatement方法的第二个参数需要指定Statement.RETURN_GENERATED_KEYS
            preparedStatement = connection.prepareStatement("INSERT INTO t_user2 (name,age,salary,sex) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            int parameterIndex = 1;
            preparedStatement.setString(parameterIndex++, userModel.getName());
            preparedStatement.setInt(parameterIndex++, userModel.getAge());
            preparedStatement.setDouble(parameterIndex++, userModel.getSalary());
            preparedStatement.setInt(parameterIndex++, userModel.getSex());
            int count = preparedStatement.executeUpdate();
            log.info("影响行数：{}", count);
            //通过查询获取自增值
            rs = connection.prepareStatement("SELECT LAST_INSERT_ID()").executeQuery();
            if (rs != null && rs.next()) {
                log.info("自增值为：{}", rs.getInt(1));
            }
        } finally {
            if (rs != null && rs.isClosed()) {
                rs.close();
            }
            if (preparedStatement != null && preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            if (connection != null && connection.isClosed()) {
                connection.close();
            }
        }
    }


    // mybatis获取主键的3种方式
    //  方式1：内部使用jdbc内置的方式
    @Test
    public  void insertUser2()
    {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            UserMapper6 mapper = sqlSession.getMapper(UserMapper6.class);
            UserModel2 um = UserModel2.builder().id(1L).name(UUID.randomUUID().toString()).age(30).salary(50000D).sex(1).build();
            int a=   mapper.insertUser2(um);
            System.out.println(a);
            System.out.println(um);
        }
    }

    // 方式2：插入后查询获取主键
    @Test
    public  void insertUser3()
    {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            UserMapper6 mapper = sqlSession.getMapper(UserMapper6.class);
            UserModel2 um = UserModel2.builder().id(1L).name(UUID.randomUUID().toString()).age(30).salary(50000D).sex(1).build();
            int a=   mapper.insertUser3(um);
            System.out.println(a);
            System.out.println(um);
        }
    }

    // 方式2：插入前查询获取主键
    @Test
    public  void insertUser4()
    {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            UserMapper6 mapper = sqlSession.getMapper(UserMapper6.class);
            UserModel2 um = UserModel2.builder().id(1L).name(UUID.randomUUID().toString()).age(60).salary(50000D).sex(1).build();
            int a=   mapper.insertUser4(um);
            System.out.println(a);
            System.out.println(um);
        }
    }


}