package com.mybatis;

import com.mybatis.mapper.OrderMapper;
import com.mybatis.model.OrderModel;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class Test8 {

    // 自动映射
    //mybatis中自动映射主要有2种配置，一种是全局的配置，对应用中所有的resultMap起效，这个是在mybatis配置文件中进行设置的；另外一种是通过resultMap的autoMapping属性进行配置。

    //mybatis判断某个resultMap是否开启自动映射配置的时候，会先查找自身的autoMapping属性，如果这个属性设置值了，就直接用这个属性的值，如果resultMap元素的autoMapping属性没有配置，则走全局配置的自动映射规则。

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

    @Test
    public void getById() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
            OrderModel om = mapper.getById10(1);
            System.out.println(om);
        }
    }


}
