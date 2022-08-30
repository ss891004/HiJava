package com.hm;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hm.entity.User2;
import com.hm.mapper.User2Mapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

public class MyBatis_Wrapper {

    private User2Mapper user2Mapper;

    @Before
    public void init() {
        ApplicationContext ac = new
                ClassPathXmlApplicationContext("spring-bean-mp.xml");
        user2Mapper = ac.getBean(User2Mapper.class);
    }

    @Test
    public void test01() {
//查询用户名包含a，年龄在20到30之间，并且邮箱不为null的用户信息
//SELECT id,username AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (username LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
        QueryWrapper<User2> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", "a")
                .between("age", 20, 30)
                .isNotNull("email");
        List<User2> list = user2Mapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }


    @Test
    public void test02() {
//按年龄降序查询用户，如果年龄相同则按id升序排列
//SELECT id,username AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 ORDER BY age DESC,id ASC
        QueryWrapper<User2> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .orderByDesc("age")
                .orderByAsc("id");
        List<User2> users = user2Mapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test03() {
//删除email为空的用户
//DELETE FROM t_user WHERE (email IS NULL)
        QueryWrapper<User2> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
//条件构造器也可以构建删除语句的条件
        int result = user2Mapper.delete(queryWrapper);
        System.out.println("受影响的行数：" + result);
    }

    @Test
    public void test04() {
        QueryWrapper<User2> queryWrapper = new QueryWrapper<>();
//将（年龄大于20并且用户名中包含有a）或邮箱为null的用户信息修改
//UPDATE t_user SET age=?, email=? WHERE (username LIKE ? AND age > ? OR  email IS NULL)
        queryWrapper
                .like("username", "a")
                .gt("age", 20)
                .or()
                .isNull("email");
        User2 user = new User2();
        user.setAge(18);
        user.setEmail("user@atguigu.com");
        int result = user2Mapper.update(user, queryWrapper);
        System.out.println("受影响的行数：" + result);
    }

    @Test
    public void test04_2() {
        QueryWrapper<User2> queryWrapper = new QueryWrapper<>();
//将（年龄大于20或邮箱为null）并且用户名中包含有a的用户信息修改
//UPDATE t_user SET age=?, email=? WHERE (username LIKE ? AND (age > ? OR  email IS NULL))
//lambda表达式内的逻辑优先运算
        queryWrapper
                .like("username", "a")
                .and(i -> i.gt("age", 20).or().isNull("email"));
        User2 user = new User2();
        user.setAge(18);
        user.setEmail("user@atguigu.com");
        int result = user2Mapper.update(user, queryWrapper);
        System.out.println("受影响的行数：" + result);
    }

    @Test
    public void test05() {
//查询用户信息的username和age字段
//SELECT username,age FROM t_user
        QueryWrapper<User2> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("username", "age");
//selectMaps()返回Map集合列表，通常配合select()使用，避免User对象中没有被查询到的列值为null
        List<Map<String, Object>> maps = user2Mapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void test06() {
//查询id小于等于3的用户信息
//SELECT id,username AS name,age,email,is_deleted FROM t_user WHERE (id IN    (select id from t_user where id <= 3))
        QueryWrapper<User2> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("id", "select id from t_user2 where id <= 3");
//selectObjs的使用场景：只返回一列
        List<Object> objects = user2Mapper.selectObjs(queryWrapper);
        objects.forEach(System.out::println);
    }

    ////////////////////////////////////////////////////////

    @Test
    public void test07() {
//将（年龄大于20或邮箱为null）并且用户名中包含有a的用户信息修改
//组装set子句以及修改条件
        UpdateWrapper<User2> updateWrapper = new UpdateWrapper<>();
//lambda表达式内的逻辑优先运算
        updateWrapper
                .set("age", 18)
                .set("email", "user@atguigu.com")
                .like("username", "a")
                .and(i -> i.gt("age", 20).or().isNull("email"));
//这里必须要创建User对象，否则无法应用自动填充。如果没有自动填充，可以设置为null
//UPDATE t_user SET username=?, age=?,email=? WHERE (username LIKE ? AND (age > ? OR email IS NULL))
//User user = new User();
//user.setName("张三");
//int result = userMapper.update(user, updateWrapper);
//UPDATE t_user SET age=?,email=? WHERE (username LIKE ? AND (age > ? OR  email IS NULL))
        int result = user2Mapper.update(null, updateWrapper);
        System.out.println(result);
    }

    @Test
    public void test08UseCondition() {
//定义查询条件，有可能为null（用户未输入或未选择）
        String username = null;
        Integer ageBegin = 10;
        Integer ageEnd = 24;
        QueryWrapper<User2> queryWrapper = new QueryWrapper<>();
//StringUtils.isNotBlank()判断某字符串是否不为空且长度不为0且不由空白符(whitespace)构成
                queryWrapper
.like(StringUtils.isNotBlank(username), "username", "a")
                .ge(ageBegin != null, "age", ageBegin)
                .le(ageEnd != null, "age", ageEnd);
//SELECT id,username AS name,age,email,is_deleted FROM t_user WHERE (age >=? AND age <= ?)
        List<User2> users = user2Mapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test09() {
//定义查询条件，有可能为null（用户未输入）
        String username = "a";
        Integer ageBegin = 10;
        Integer ageEnd = 24;
        LambdaQueryWrapper<User2> queryWrapper = new LambdaQueryWrapper<>();
//避免使用字符串表示字段，防止运行时错误
        queryWrapper
                .like(StringUtils.isNotBlank(username), User2::getName, username)
                .ge(ageBegin != null, User2::getAge, ageBegin)
                .le(ageEnd != null, User2::getAge, ageEnd);
        List<User2> users = user2Mapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test10() {
//组装set子句
        LambdaUpdateWrapper<User2> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(User2::getAge, 18)
                .set(User2::getEmail, "user@atguigu.com")
                .like(User2::getName, "a")
                .and(i -> i.lt(User2::getAge, 24).or().isNull(User2::getEmail)); //lambda 表达式内的逻辑优先运算
        User2 user = new User2();
        int result = user2Mapper.update(user, updateWrapper);
        System.out.println("受影响的行数：" + result);
    }

}


