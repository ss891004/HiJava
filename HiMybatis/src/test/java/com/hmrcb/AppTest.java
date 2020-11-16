package com.hmrcb;

import static org.junit.Assert.assertTrue;

import com.hmrcb.entity.User;
import com.hmrcb.mapper.UserMapper;
import com.hmrcb.mapper.UserMapper2;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

//表的CRUD
public class AppTest {
    @Test
    public void test1() throws IOException {
        // 1. 加载配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

        // 2. 构建 SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 3. 通过SqlSessionFactory 创建 SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //4. 通过SqlSession 获得 DAO实现类的对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);//获取UserDAO对应的实现类的对象

        //新增用户
        User new_user = new User(null, "shine_222", "00000", true, new Date());
        mapper.insertUser(new_user);
        sqlSession.commit();
        System.out.println(new_user);

    }

    @Test
    public void test2() throws IOException {
        // 1. 加载配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

        // 2. 构建 SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 3. 通过SqlSessionFactory 创建 SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //新增用户
        User new_user = new User(null, "shine_222", "00000", true, new Date());
        sqlSession.insert("com.hmrcb.mapper.UserMapper.insertUser", new_user);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 测试注解的方式
     *
     * @throws IOException
     */
    @Test
    public void test3() throws IOException {
        // 1. 加载配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

        // 2. 构建 SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 3. 通过SqlSessionFactory 创建 SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //4. 通过SqlSession 获得 DAO实现类的对象
        UserMapper2 mapper = sqlSession.getMapper(UserMapper2.class);//获取UserDAO对应的实现类的对象

        //新增用户
        User new_user = new User(null, "shine_222", "00000", true, new Date());
        mapper.insertUser(new_user);
        sqlSession.commit();

        System.out.println(mapper.selectUser());

    }


}
