package com.hm.mapper;

import com.hm.entity.User;

import java.util.List;

public interface TestMapper {
    /**
     * 查询所有用户信息
     * @return
     */
    List<User> getAllUser();
}