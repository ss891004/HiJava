package com.hm.config;

import com.hm.service.EmpService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmpConfig {
    /**
     * @Bean 标识的方法用于向容器注入组件
     * 1. 方法的返回值就是注入容器中的组件对象,
     * 2. 方法名是这个组件对象的 id 值
     */
    @Bean
    public EmpService empService2() {
        System.out.println(" @Bean 注解已经将 EmpService 组件注入");
        return new EmpService();
    }
}
