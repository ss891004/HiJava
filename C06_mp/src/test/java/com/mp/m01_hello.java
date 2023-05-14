package com.mp;

import com.mp.entity.Employee;
import com.mp.mapper.EmployeeMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class m01_hello {

    // 创建表
    private EmployeeMapper employeeMapper;

    @Before
    public void init() {
        ApplicationContext ac = new
                ClassPathXmlApplicationContext("spring-bean-mp.xml");
        employeeMapper = ac.getBean(EmployeeMapper.class);
    }


    @Test
    public void testMyBatis_plus() {
        System.out.println(employeeMapper.selectById(1));
    }

    @Test
    public void testInsert() {
        Employee user = new Employee(null, "张三", "zhangsan@atguigu.com", 1, 23);
        int result = employeeMapper.insert(user);
        System.out.println("受影响行数：" + result);
        System.out.println("id自动获取：" + user.getId());
    }

    @Test
    public void testUpdateById() {
        Employee user = new Employee(1657311006606192641L, "admin", null, 1, 22);
        int result = employeeMapper.updateById(user);
        System.out.println("受影响行数：" + result);
    }

    @Test
    public void testSelectById() {
//根据id查询用户信息
//SELECT id,name,age,email FROM user WHERE id=?
        Employee user = employeeMapper.selectById(1657311006606192641L);
        System.out.println(user);
    }


    @Test
    public void testSelectList() {
//查询所有用户信息
//SELECT id,name,age,email FROM user
        List<Employee> list = employeeMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    public void testSelectByMap() {
//通过map条件查询用户信息
//SELECT id,name,age,email FROM user WHERE name = ? AND age = ?
        Map<String, Object> map = new HashMap<>();
        map.put("age", 22);
        map.put("last_Name", "admin");
        List<Employee> list = employeeMapper.selectByMap(map);
        list.forEach(System.out::println);
    }


    @Test
    public void testSelectBatchIds() {
//根据多个id查询多个用户信息
//SELECT id,name,age,email FROM user WHERE id IN ( ? , ? )
        List<Long> idList = Arrays.asList(4L, 5L);
        List<Employee> list = employeeMapper.selectBatchIds(idList);
        list.forEach(System.out::println);
    }


    @Test
    public void testDeleteById() {
//通过id删除用户信息
        int result = employeeMapper.deleteById(7);
        System.out.println("受影响行数：" + result);
    }

    @Test
    public void testDeleteBatchIds() {
//通过多个id批量删除
//DELETE FROM user WHERE id IN ( ? , ? , ? )
        List<Integer> idList = Arrays.asList(1, 2, 3, 4, 5);
        int result = employeeMapper.deleteBatchIds(idList);
        System.out.println("受影响行数：" + result);
    }


    @Test
    public void testDeleteByMap() {
//根据map集合中所设置的条件删除记录
//DELETE FROM user WHERE name = ? AND age = ?
        Map<String, Object> map = new HashMap<>();
        map.put("age", 23);
        map.put("last_Name", "张三");
        int result = employeeMapper.deleteByMap(map);
        System.out.println("受影响行数：" + result);
    }
}
