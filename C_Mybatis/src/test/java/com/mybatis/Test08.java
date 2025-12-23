package com.mybatis;

import com.mybatis.mapper.UserMapper10;
import com.mybatis.model.UserModel;
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

public class Test08 {

    // 动态SQL
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

    // if 元素
    @Test
    public void Test1() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            UserMapper10 mapper = sqlSession.getMapper(UserMapper10.class);
            Map<String, String> param = new HashMap<>();
            param.put("id", "8");
            param.put("name", "路人甲Java-2");
            param.put("age", "32");
            List<UserModel> user = mapper.getList1(param);
            System.out.print(user);
        }
    }

    //choose/when/otherwise 元素
    @Test
    public void Test2() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            UserMapper10 mapper = sqlSession.getMapper(UserMapper10.class);
            Map<String, String> param = new HashMap<>();
            // param.put("id", "8");
            param.put("name", "路人甲Java-2");
            param.put("age", "32");
            List<UserModel> user = mapper.getList2(param);
            System.out.print(user);
        }
    }

    //where 元素
    @Test
    public void Test3() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            UserMapper10 mapper = sqlSession.getMapper(UserMapper10.class);
            Map<String, String> param = new HashMap<>();
            // param.put("id", "8");
            //param.put("name","路人甲Java-2");
            param.put("age", "32");
            List<UserModel> user = mapper.getList3(param);
            System.out.print(user);
        }
    }

    // set 元素
    @Test
    public void Test4() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            UserMapper10 mapper = sqlSession.getMapper(UserMapper10.class);
            Map<String, String> param = new HashMap<>();
            param.put("id", "8");
            param.put("name", "路人甲Java-2xxxx");
            param.put("age", "3332");
            System.out.println(mapper.update1(param));
            param.put("age", "1234");
            System.out.println(mapper.update2(param));
        }
    }

    // trim 元素， 处理步骤：
    //先对trim内部的sql进行拼接，比如这部分sql叫做sql1
    //将sql1字符串前面的部分中包含trim的prefixOverrides指定的部分给去掉，得到sql2
    //将sql2字符串后面的部分中包含trim的suffixOverrides指定的部分给去掉，得到sql3
    //在sql3前面追加trim中prefix指定的值，得到sql4
    //在sql4后面追加trim中suffix指定的值，得到最终需要拼接的sql5


    // foreach元素
    @Test
    public void Test5() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            UserMapper10 mapper = sqlSession.getMapper(UserMapper10.class);
            Map<String, Object> param = new HashMap<>();
            List<String> a= new ArrayList<>();
            a.add("8");
            a.add("9");
            param.put("idList", a);
            System.out.println(mapper.getList5(param));
        }
    }

    @Test
    public void insertBatch() throws IOException {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            UserMapper10 mapper = sqlSession.getMapper(UserMapper10.class);
            List<UserModel> userModelList = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                userModelList.add(UserModel.builder().id( new Long(100 + i) ).name("mybatis-" + i).age(20 + i).build());
            }
            int count = mapper.insertBatch(userModelList);
        }
    }

    // sql/include元素
    @Test
    public void Test6() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            UserMapper10 mapper = sqlSession.getMapper(UserMapper10.class);
            Map<String, Object> param = new HashMap<>();
            List<String> a= new ArrayList<>();
            a.add("8");
            a.add("9");
            param.put("idList", a);
            System.out.println(mapper.getList1Count(param));
        }
    }

    // bind元素
    @Test
    public void Test7() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            UserMapper10 mapper = sqlSession.getMapper(UserMapper10.class);
            Map<String, Object> param = new HashMap<>();
            param.put("likeName","java");

            System.out.println(mapper.getList1Count(param));
        }
    }

    //#{}:为参数占位符？，即sql预编译，相当于使用jdbc中的PreparedStatement中的sql占位符，可以防止sql注入
    //${}:为字符串替换， 即字符串拼接，不能访问sql注入。




}
