package com.mybatis.mapper;


import com.mybatis.model.UserModel;

import java.util.List;
import java.util.Map;

public interface UserMapper12 {

    List<UserModel> getList1(Map user);

    List<UserModel> getList2(Map user);

    List<UserModel> getList3(Map user);

    int insertBatch(List<UserModel> userModel);

}
