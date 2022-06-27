package com.hm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取用户请求发送的数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println("提交的数据：" + username + "\t" + password);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //对request请求对象设置统一的编码
        req.setCharacterEncoding("utf-8");
        //1.获取用户请求发送的数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //2.响应数据给客户端

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("注册成功！");
    }
}