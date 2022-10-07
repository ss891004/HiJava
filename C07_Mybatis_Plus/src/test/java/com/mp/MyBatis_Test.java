package com.mp;

import com.mp.mapper.TestMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyBatis_Test {


    @Test
    public void testMyBatis() {
        ApplicationContext ac = new
                ClassPathXmlApplicationContext("spring-bean.xml");
        TestMapper mapper = ac.getBean(TestMapper.class);
        mapper.getAllUser().forEach(user -> System.out.println(user));
    }
}
