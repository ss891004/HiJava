package com.hm.mybatis.mapper;

import com.hm.mybatis.model.UserModel;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    //插入用户信息
    void insert(UserModel userModel);

    //批量插入用户信息
    void insertBatch(List<UserModel> userModelList);

    //更新用户信息
    int update(UserModel userModel);

    // 通过map来更新用户记录
    int updateByMap(Map<String, Object> map);

    /**
     * 通过map来删除用户记录
     *
     * @param map
     * @return
     */
    int delete(Map<String, Object> map);

    //查询用户列表
    List<UserModel> getModelList(Map<String, Object> map);




}