package com.mp.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

// 生命周期
@WebServlet(name = "servlet1",value = "/servlet1")
public class HelloServlet implements Servlet {
    //当用户第一次访问servlet时,由容器调用servlet的构造器创建具体对象,也可以在容器启动之后立即创建实例.
    //只执行一次
    public HelloServlet(){
        System.out.println("1、实例化");
    }

    //只执行一次
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("2、初始化");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    //执行多次
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("3、接收请求，响应结果");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    //当容器重启或停止时,只执行一次
    @Override
    public void destroy() {
        System.out.println("4、销毁");
    }
}
