package com.mp.controller;

import com.mp.entity.Emp;
import com.mp.service.EmpService;
import com.mp.service.impl.EmpServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "InsertEmpController", value = "/manager/safe/insertEmp")
public class InsertEmpController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        Double salary = Double.valueOf(request.getParameter("salary"));
        Integer age = Integer.valueOf(request.getParameter("age"));

        Emp emp = new Emp(name, salary, age);

        EmpService empService = new EmpServiceImpl();
        empService.addEmp(emp);

        response.sendRedirect(request.getContextPath() + "/manager/safe/showAllEmp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
