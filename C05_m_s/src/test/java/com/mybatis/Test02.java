package com.mybatis;

import com.mybatis.mapper.UserMapper3;
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
import java.util.List;

//Mybatis使用详解
@Slf4j
public class Test02 {
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
    public void insertUser() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            //动态代理实现的
            UserMapper3 mapper = sqlSession.getMapper(UserMapper3.class);
            //创建UserModel对象
            UserModel2 userModel2 = UserModel2.builder().id(System.currentTimeMillis()).name("路人甲Java").age(30).salary(50000D).sex(1).build();
            //执行插入操作
            int insert = mapper.insertUser(userModel2);
            log.info("影响行数：{}", insert);
        }
    }

    @Test
    public void updateUser() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            UserMapper3 mapper = sqlSession.getMapper(UserMapper3.class);
            //创建UserModel对象
            UserModel2 userModel2 = UserModel2.builder().id(1612791474497L).name("路人甲Jav333333a，你好").age(18).salary(5000D).sex(0).build();
            //执行更新操作
            int result = mapper.updateUser(userModel2);
            log.info("影响行数：{}", result);
        }
    }

    @Test
    public void deleteUser() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            UserMapper3 mapper = sqlSession.getMapper(UserMapper3.class);
            //定义需要删除的用户id
            Long userId = 2L;
            //执行删除操作
            int result = mapper.deleteUser(userId);
            log.info("影响行数：{}", result);
        }
    }

    @Test
    public void getUserList() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            UserMapper3 mapper = sqlSession.getMapper(UserMapper3.class);
            //执行查询操作
            List<UserModel2> userModel2List = mapper.getUserList();
            userModel2List.forEach(item -> {
                System.out.println(item.getId());
            });
        }
    }


}