package com.hm;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync //开启基于注解的异步处理
@SpringBootApplication
@EnableCaching
@EnableScheduling
public class App {
    public static void main(String[] args) {

        SpringApplication.run(App.class, args);
    }
}
