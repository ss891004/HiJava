package com.hmrcb;

import static org.junit.Assert.assertTrue;

import com.hmrcb.dao.CustomerDao;
import com.hmrcb.entity.Customer;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }


    @Test
    public void TestMybais() throws IOException {

        // 配置文件
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        // 使用SqlSessionFactoryBuilder从xml配置文件中创建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder()
                .build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

    }


}
