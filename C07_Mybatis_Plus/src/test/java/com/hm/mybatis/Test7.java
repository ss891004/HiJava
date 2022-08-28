package com.hm.mybatis;

import com.hm.mybatis.mapper.OrderMapper;
import com.hm.mybatis.model.OrderModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

//各种查询详解
@Slf4j
public class Test7 {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws IOException {
        //指定mybatis全局配置文件
        String resource = "mybatis-config.xml";
        //读取全局配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //构建SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        this.sqlSessionFactory = sqlSessionFactory;
    }

    //4张表:
    //t_user(用户表)
    //t_goods(商品表)
    //t_order(订单表)
    //t_order_detail(订单明细表)
    //
    //表之间的关系：
    //t_order和t_user是一对一的关系，一条订单关联一个用户记录
    //t_order和t_order_detail是一对多关系，每个订单中可能包含多个子订单，每个子订单对应一个商品

    // 栏位用别名
    @Test
    public void getById() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
            OrderModel om = mapper.getById(1);
            System.out.println(om);
        }
    }


    //开启自动驼峰命名规则映射
    @Test
    public void getById2() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);

            OrderModel om = mapper.getById2(1);
            System.out.println(om);

        }
    }

    //resultMap
    @Test
    public void getById3() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);

            OrderModel om = mapper.getById3(2);
            System.out.println(om);

        }
    }

    //一对一关联查询
    // 通过订单id查询订单的时候，将订单关联的用户信息也返回。
    @Test
    public void getById4() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);

            OrderModel om = mapper.getById4(2);
            System.out.println(om);

        }
    }

    // association，这个元素可以配置关联对象的映射关系
    @Test
    public void getById5() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);

            OrderModel om = mapper.getById5(2);
            System.out.println(om);
        }
    }

    @Test
    public void getById6() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);

            OrderModel om = mapper.getById6(2);
            System.out.println(om);
        }
    }

    //先按照订单id查询订单数据，然后在通过订单中user_id去用户表查询用户数据，通过两次查询，组合成目标结果
    @Test
    public void getById7() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);

            OrderModel om = mapper.getById7(2);
            System.out.println(om);
        }
    }

    // 一对多查询(2种方式)
    // 根据订单id查询出订单信息，并且查询出订单明细列表。
    @Test
    public void getById8() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);

            OrderModel om = mapper.getById8(1);
            System.out.println(om);
        }
    }


    @Test
    public void getById9() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);

            OrderModel om = mapper.getById9(1);
            System.out.println(om);

        }
    }

}