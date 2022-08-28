package com.hm.service;

import com.hm.model.User1Model;
import com.hm.model.User2Model;

import java.util.List;

public interface UserService {

    // 插入用户信息
    User1Model insert(User1Model userModel);

    User2Model insert2(User2Model userModel);

    // 查询用户所有记录
    List<User1Model> getList();

    // 查询用户所有记录
    List<User2Model> getList2();

    //整合mongodb

    User1Model insert3(User1Model userModel);

    List<User1Model> getList3();



}
