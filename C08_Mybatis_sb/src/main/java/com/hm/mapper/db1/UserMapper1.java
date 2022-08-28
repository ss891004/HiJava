package com.hm.mapper.db1;

import com.hm.model.User1Model;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper1 {
    void insert(User1Model userModel);
    List<User1Model> getList();
}