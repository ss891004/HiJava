package com.hm.mybatis;

import com.hm.mybatis.mapper.UserMapper;
import com.hm.mybatis.model.UserModel;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class Test1 {

    // mybatis 入门

    //通过接口文件，找到xml中对应的方法
    @Test
    public void test1() throws IOException {
        // 1. 加载配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

        // 2. 构建 SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 3. 通过SqlSessionFactory 创建 SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //4. 通过SqlSession 获取UserDAO对应的实现类的对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        // 新增用户
        UserModel new_user = UserModel.builder().name("test1-test1").age(32).salary(50000.00).build();
        // 在config配置文件中，需要使用mapper节点注册本接口文件
        // 若mapper的xml文件在类文件的一起，需要和接口名字一样，若在resource 中可以不一样，需要在注册接口是指定
        mapper.insert(new_user);
        sqlSession.commit();
        sqlSession.close();
        System.out.println(new_user);
    }

    //通过全路径的包+方法，找到具体的方法
    @Test
    public void test2() throws IOException {
        // 1. 加载配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

        // 2. 构建 SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 3. 通过SqlSessionFactory 创建 SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //新增用户
        UserModel new_user = UserModel.builder().name("test1-test2").age(88).salary(8888.00).build();
        int record= sqlSession.insert("com.hm.mybatis.mapper.UserMapper.insert", new_user);
        sqlSession.commit();
        sqlSession.close();
        System.out.print(record);
    }

}