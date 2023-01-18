package com.mp.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//响应的重定向
@WebServlet(name = "servlet3",value = "/servlet3")
public class HelloServlet3 extends HttpServlet {

    //设置Location响应头，实现请求重定向
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(302);//设置服务器的响应状态码
        //注意context path
        resp.setHeader("Location", "/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
