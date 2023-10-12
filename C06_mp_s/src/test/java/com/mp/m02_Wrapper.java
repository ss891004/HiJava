package com.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mp.entity.Employee;
import com.mp.mapper.EmployeeMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

// 条件构造器
public class m02_Wrapper {
    /*
    * ## 使用库
    USE mp;
    ## 清空表
    TRUNCATE TABLE tbl_employee;
    ## 导入数据
    INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Allan0','123@qq.com',0,21);
    INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Allan1','123@qq.com',0,22);
    INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Allan2','123@qq.com',0,23);
    INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Allan3','123@qq.com',0,24);
    INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Allan4','123@qq.com',0,25);
    INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Allan5','123@qq.com',0,26);
    INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Allan6','123@qq.com',0,27);
    INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Allan7','123@qq.com',0,28);
    INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Allan8','123@qq.com',0,29);
    INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Allan9','123@qq.com',0,30);
    INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Baby0','123@qq.com',1,21);
    INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Baby1','123@qq.com',0,22);
    INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Baby2','123@qq.com',1,23);
    INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Baby3','123@qq.com',0,24);
    INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Baby4','123@qq.com',1,25);
    INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Baby5','123@qq.com',0,26);
    INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Baby6','123@qq.com',1,27);
    INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Baby7','123@qq.com',0,28);
    INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Baby8','123@qq.com',1,29);
    INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Baby9','123@qq.com',0,30);
    INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Tom0','123@qq.com',1,21);
    INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Tom1','123@qq.com',0,22);
    INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Tom2','123@qq.com',1,23);
    INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Tom3','123@qq.com',0,24);
    INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Tom4','123@qq.com',1,25);
    INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Tom5','123@qq.com',0,26);
    INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Tom6','123@qq.com',1,27);
    INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Tom7','123@qq.com',0,28);
    INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Tom8','123@qq.com',1,29);
    INSERT INTO tbl_employee(last_name,email,gender,age) VALUES('Tom9','123@qq.com',0,30);
    ## 查询数据
    SELECT * FROM tbl_employee;
    */
    private EmployeeMapper employeeMapper;

    /**
     * MyBatis-Plus 通过 EntityWrapper（简称 EW，MP 封装的一个查询条件构造器）或者 Condition（与 EW 类似）
     * 来让用户自由的构建查询条件，简单便捷，没有额外的负担， 能够有效提高开发效率，它主要用于处理 sql 拼接，排序，
     * 实体参数查询等。
     */

    @Before
    public void init() {
        ApplicationContext ac = new
                ClassPathXmlApplicationContext("spring-bean-mp.xml");
        employeeMapper = ac.getBean(EmployeeMapper.class);
    }

    /**
     * 带条件的查询
     */
    @Test
    public void test01() {

        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();

        queryWrapper
                .like("last_name", "B")
                .gt("age", 24)
                .eq("gender", 1);

        // 需求描述：查询所有姓名的包含B、且姓名为女（1）、且年龄大于24岁的员工信息
        List<Employee> employees = employeeMapper.selectList(queryWrapper);
        employees.forEach(System.out::println);


        // 需求描述：查询所有员工信息
        employees = employeeMapper.selectList(null);
        employees.forEach(System.out::println);


        //需求描述：查询所有女生的数量
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("gender", 1);
        Long count = employeeMapper.selectCount(queryWrapper);
        System.out.println(count);
    }

    /**
     * 带条件的修改
     */

    @Test
    public void test2() {

        //需求信息：将年龄大于25岁的女生（1）的性别修改为男生（0）
        UpdateWrapper<Employee> updateWrapper = new UpdateWrapper<>();
        updateWrapper
                .eq("gender", 1)
                .gt("age", 10)
        ;
        Employee employee = new Employee();
        employee.setGender(0);
        employeeMapper.update(employee, updateWrapper);

    }

    /**
     * 带条件的删除
     */

    @Test
    public void test3() {

        //需求信息：将姓名带有“Tom”的员工信息删除
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("last_name", "Tom");
        int result = employeeMapper.delete(queryWrapper);
        System.out.println(result);
    }




}

