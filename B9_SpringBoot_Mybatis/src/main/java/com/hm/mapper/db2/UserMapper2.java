package com.hm.mapper.db2;

import com.hm.model.User2Model;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper2 {
    void insert(User2Model user2Model);
    List<User2Model> getList();
}