package com.hmrcb;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(name = "servlet1",value = "/servlet1")
public class HelloServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("初始化servlet-----------init------"+this.hashCode());
    }

    @Override
    public ServletConfig getServletConfig() {
        System.out.println("获取Servlet配置------------getServletConfig----"+this.hashCode());
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("服务方法---------------service--------"+this.hashCode());
    }

    @Override
    public String getServletInfo() {
        System.out.println("获取servlet基本信息----------------getServletInfo");
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("------销毁----------destroy---------"+this.hashCode());
    }
}
