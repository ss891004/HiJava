package com.mybatis.mapper;

import com.mybatis.dto.UserFindDto;
import com.mybatis.model.UserModel2;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface UserMapper4 {

    UserModel2 getByName(String name);

    List<UserModel2> getByMap(Map<String, Object> map);

    List<UserModel2> getListByUserFindDto(UserFindDto userFindDto);

    UserModel2 getByIdOrName1(Long id, String name);

    UserModel2 getByIdOrName2(Long id, String name);

    UserModel2 getByIdOrName3(@Param("userId") Long id, @Param("userName") String name);

    List<UserModel2> getListByIdCollection(Collection<Long> idCollection);

}
