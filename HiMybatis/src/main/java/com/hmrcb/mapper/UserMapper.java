package com.hmrcb.mapper;

import com.hmrcb.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

//基于XML的实现
public interface UserMapper {
    //序号参数绑定
    User queryUserById(Integer id);
    //序号参数绑定
    User queryUserByIdAndUsername(Integer id,String username);
    //注解参数绑定
    User queryUserByIdAndPassword(@Param("id") Integer id, @Param("password") String password);
    // map参数绑定
    User queryUserByIdAndPassword2(Map map);
    //对象参数绑定
    User queryUserByIdAndPassword3(User user);
    //模糊查询, 拼接%
    List<User> queryUserByUsername(@Param("username") String username);

    Integer deleteUser(@Param("id") Integer id);

    Integer updateUser(User user);

    Integer insertUser(User user);

    Integer insertUser2(User user);
}
