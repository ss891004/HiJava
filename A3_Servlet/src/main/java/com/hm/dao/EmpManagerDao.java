package com.hm.dao;

import com.hm.entity.EmpManager;

public interface EmpManagerDao {
    EmpManager select(String username);
}
