package com.hmrcb;

import com.hmrcb.entity.Order;
import com.hmrcb.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class AppTest2 {
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void test() throws IOException {
        // 1. 加载配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

        // 2. 构建 SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 3. 通过SqlSessionFactory 创建 SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        List<Order> ls = sqlSession.selectList("com.hmrcb.mapper.OrderMapper.getOrderById");
        List<Order> ls2 = sqlSession.selectList("com.hmrcb.mapper.OrderMapper.selectOrder",1);
        List<Order> ls3 = sqlSession.selectList("com.hmrcb.mapper.OrderMapper.selectOrderResultMap",2);

        System.out.println(ls2);
        System.out.println(ls2);
        System.out.println(ls3);
    }
}
