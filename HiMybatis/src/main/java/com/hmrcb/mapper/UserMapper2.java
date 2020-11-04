package com.hmrcb.mapper;

import com.hmrcb.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

//基于注解的实现
public interface UserMapper2 {

    @Select("select id,username,password,gender,regist_time as registTime from t_user")
    List<User> selectUser();

    @Delete(" delete from t_user where id=#{id}")
    Integer deleteUser(Integer id);

    @Update("update t_user set username=#{username},password=#{password},gender=#{gender},regist_time=#{registTime} where id=#{id}")
    Integer updateUser(User user);

    @Insert("insert into t_user values(#{id},#{username},#{password},#{gender},#{registTime})")
    Integer insertUser(User user);
}
