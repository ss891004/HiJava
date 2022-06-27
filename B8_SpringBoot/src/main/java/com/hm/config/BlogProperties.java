package com.hm.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogProperties {
    @Value("${com.ss.blog.name}")
    private String name;
    @Value("${com.ss.blog.title}")
    private String title;
    @Value("${com.ss.blog.age}")
    private  String age;
}