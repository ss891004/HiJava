package com.mybatis;

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
public class Test01 {

    /*
    * SqlSession相当于一个连接，可以使用这个对象对db执行增删改查操作，操作完毕之后需要关闭，使用步骤：
    *   1.获取SqlSession对象：通过该sqlSessionFactory.openSession方法获取SqlSession对象
    *   2.对db进行操作：使用SqlSession对象进行db操作
    *   3.关闭SqlSession对象：sqlSession.close();
    * */

    /*
    * Mapper接口使用时注意的几点
        Mapper接口的完整类名必须和对应的Mapper xml中的namespace的值一致
        Mapper接口中方法的名称需要和Mapper xml中具体操作的id值一致
        Mapper接口中方法的参数、返回值可以不和Mapper xml中的一致
    */

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
    public void sqlSession() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession()) {
            //执行业务操作，如：增删改查
            System.out.println(sqlSession);
            log.info("{}", sqlSession);
        }
    }

    //新增操作
    @Test
    public void insertUser() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(false)) {
            //创建UserModel对象
            UserModel2 userModel = UserModel2.builder().name("inset-a").age(30).salary(50000D).sex(1).build();

            //执行插入操作 找到某个xml中的某个insert 段
            int result = sqlSession.insert("com.mybatis.mapper.UserMapper3.insertUser", userModel);
            log.info("插入影响行数：{}", result);
            //提交事务
            sqlSession.commit();
        }
    }

    @Test
    public void insertUser2() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            //创建UserModel对象
            UserModel2 userModel = UserModel2.builder().id(1L).name("insert-b").age(30).salary(50000D).sex(1).build();
            //执行插入操作
            int result = sqlSession.insert("com.hm.mybatis.mapper.UserMapper2.insertUser", userModel);
            log.info("影响行数：{}", result);
        }
    }

    //更新操作
    @Test
    public void updateUser() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            //创建UserModel对象
            UserModel2 userModel = UserModel2.builder().id(1L).name("update-a").age(18).salary(5000D).sex(0).build();
            //执行更新操作
            int result = sqlSession.update("com.mybatis.mapper.UserMapper3.updateUser", userModel);
            log.info("影响行数：{}", result);
        }
    }

    //删除操作
    @Test
    public void deleteUser() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            //定义需要删除的用户id
            Long userId = 1L;
            //执行删除操作
            int result = sqlSession.delete("com.mybatis.mapper.UserMapper3.deleteUser", userId);
            log.info("影响行数：{}", result);
        }
    }

    // 查询操作
    @Test
    public void selectUser() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            List<UserModel2> usr = sqlSession.selectList("com.mybatis.mapper.UserMapper3.getUserList");
            log.info("结果：{}", usr);
        }
    }
}