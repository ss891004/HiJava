package com.hmrcb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "servlet3",value = "/servlet3")
public class HelloServlet3 extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        resp.setStatus(302);//设置服务器的响应状态码
        resp.setHeader("Location", "/index.jsp");

        //resp.setContentType("text/html;charset=UTF-8");
        //resp.getWriter().print("我是Servlet创建的第一种方式");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
