package com.hmrcb;

import com.hmrcb.entity.User;
import com.hmrcb.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {

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
}
