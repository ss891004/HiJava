package com.hm.mybatis.mapper;

import com.hm.mybatis.model.UserModel2;

public interface UserMapper6 {

    /**
     * 插入用户信息，返回影响行数
     *
     */
    int insertUser(UserModel2 model);

    //mybatis获取自增值的3种方式详解

    //方式1：内部使用jdbc内置的方式
    int insertUser2(UserModel2 model);

    //方式2：插入后查询获取主键
    int insertUser3(UserModel2 model);

    //方式3：插入前查询获取主键
    int  insertUser4(UserModel2 model);

    /**
     * 更新用户信息，返回影响行数
     */
    long updateUser(UserModel2 model);

    /**
     * 根据用户id删除用户信息，返回删除是否成功
     */
    boolean deleteUser(Long userId);

}
