package com.hmrcb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/hello1")
    public String hello1(){
        System.out.println("hello this is hello1 function!!!!");
        return "index";  //index.jsp
    }

    @RequestMapping("/hello2")
    public String hello2(){
        System.out.println("hello this is hello2 function");
        return "views/users"; // views/user.jsp
    }
}