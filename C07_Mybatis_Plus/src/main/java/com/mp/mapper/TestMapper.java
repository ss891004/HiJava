package com.mp.mapper;

import com.mp.entity.User;

import java.util.List;

public interface TestMapper {
    /**
     * 查询所有用户信息
     * @return
     */
    List<User> getAllUser();
}