package com.hm;

import com.hm.config.BlogProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SBValueTest {

    @Autowired
    private BlogProperties blogProperties;

    @Test
    public void getHello() throws Exception {
        System.out.println(blogProperties.getName());
        System.out.printf(blogProperties.getTitle());
        System.out.printf(blogProperties.getAge());
    }
}


