package com.hm;

import com.hm.dao.User2;
import com.hm.dao.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

// 整合JPA
@RunWith(SpringRunner.class)
@SpringBootTest
public class SBJPATests {
    @Autowired
    UserRepository userRepository;

    @Test
    public void contextLoads() throws SQLException {

        User2 u= new User2();
        u.setAge(20);
        u.setUName("test JPA");
        u.setMail("12345@qq.com");

        userRepository.save(u);
    }
}