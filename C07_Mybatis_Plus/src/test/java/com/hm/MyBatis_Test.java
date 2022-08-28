package com.hm;

import com.hm.entity.User;
import com.hm.mapper.TestMapper;
import com.hm.mapper.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBatis_Test {


    @Test
    public void testMyBatis() {
        ApplicationContext ac = new
                ClassPathXmlApplicationContext("spring-bean.xml");
        TestMapper mapper = ac.getBean(TestMapper.class);
        mapper.getAllUser().forEach(user -> System.out.println(user));
    }
}
