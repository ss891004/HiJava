package com.hmrcb;

import com.hmrcb.entity.Order;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertTrue;

// 一对一，一对多的查询
public class AppTest3 {

    @Test
    public void test() throws IOException {
        // 1. 加载配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

        // 2. 构建 SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 3. 通过SqlSessionFactory 创建 SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        List<Order> ls2 = sqlSession.selectList("com.hmrcb.mapper.ClassMapper.getClass", 1);
        List<Order> ls3 = sqlSession.selectList("com.hmrcb.mapper.ClassMapper.getClass2", 1);
        List<Order> ls4 = sqlSession.selectList("com.hmrcb.mapper.ClassMapper.getClass3", 1);
        List<Order> ls5 = sqlSession.selectList("com.hmrcb.mapper.ClassMapper.getClass4", 1);

        System.out.println(ls2);
        System.out.println(ls3);
        System.out.println(ls4);
        System.out.println(ls5);
    }
}
