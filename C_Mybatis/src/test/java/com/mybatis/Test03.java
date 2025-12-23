package com.mybatis;

import com.mybatis.dto.UserFindDto;
import com.mybatis.mapper.UserMapper4;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Mapper接口多种方式传参
@Slf4j
public class Test03 {
    private SqlSessionFactory sqlSessionFactory;

    /*
    通过junit运行每个@Test标注的方法之前，会先运行被@before标注的方法，before()方法中我们创建了SqlSessionFactory对象，
    所以其他的@Test标注的方法中可以直接使用sqlSessionFactory对象了。
    */
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

    //传递一个参数
    //传递一个Map参数
    //传递一个javabean参数
    //多参数中用@param指定参数名称
    //java编译中参数名称的处理
    //mapper接口传参源码分析
    //传递1个Collection参数
    //传递1个List参数
    //传递1个数组参数
    //mybatis对于集合处理源码分析
    //ResultHandler作为参数的用法

    @Test
    public void getByName() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            UserMapper4 mapper = sqlSession.getMapper(UserMapper4.class);
            //执行查询操作
            UserModel2 um2 = mapper.getByName("asdfghjkl");
            System.out.println(um2);
        }
    }

    @Test
    public void getByMap() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            UserMapper4 mapper = sqlSession.getMapper(UserMapper4.class);

            Map<String, Object> map = new HashMap<>();
            map.put("id", 2L);
            map.put("name", "asdfghjkl");

            //执行查询操作
            List<UserModel2> um2 = mapper.getByMap(map);
            System.out.println(um2);
        }
    }

    @Test
    public void getListByUserFindDto() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            UserMapper4 mapper = sqlSession.getMapper(UserMapper4.class);

            UserFindDto userFindDto = UserFindDto.builder().userId(1612797927957L).userName("asdfghjkl").build();

            //执行查询操作
            List<UserModel2> um2 = mapper.getListByUserFindDto(userFindDto);
            System.out.println(um2);
        }
    }

    @Test
    public void getByIdOrName() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            UserMapper4 mapper = sqlSession.getMapper(UserMapper4.class);

            //arg0, agr1
            UserModel2 um1 = mapper.getByIdOrName1(1612797927957L, "asdfghjkl");
            System.out.println(um1);

            //param1,param2
            UserModel2 um2 = mapper.getByIdOrName2(1612797927957L, "asdfghjkl");
            System.out.println(um2);

            //指定参数名
            UserModel2 um3 = mapper.getByIdOrName3(1612797927957L, "asdfghjkl");
            System.out.println(um3);
        }
    }

    @Test
    public void getListByIdCollection() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            UserMapper4 mapper = sqlSession.getMapper(UserMapper4.class);

            List<Long> ids = new ArrayList<Long>();
            ids.add(1L);
            ids.add(2L);
            List<UserModel2> um1 = mapper.getListByIdCollection(ids);
            System.out.println(um1);

        }
    }

}