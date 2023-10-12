package com.mybatis.mapper;

import com.mybatis.model.UserModel2;

import java.util.List;

public interface UserMapper3 {

    int insertUser(UserModel2 userModel2);

    int updateUser(UserModel2 userModel2);

    int deleteUser(long id);

    List<UserModel2> getUserList();



}
