package com.mybatis.mapper;


import com.mybatis.model.UserModel;

import java.util.List;
import java.util.Map;

public interface UserMapper10 {

    List<UserModel> getList1(Map user);

    List<UserModel> getList2(Map user);

    List<UserModel> getList3(Map user);

    int update1(Map userModel);

    List<UserModel> getList4(Map user);

    int update2(Map userModel);

    List<UserModel> getList5(Map user);


    int insertBatch(List<UserModel> userModel);


    int getList1Count(Map user);

}
