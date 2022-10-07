package com.mp.dao;

import com.mp.entity.EmpManager;

public interface EmpManagerDao {
    EmpManager select(String username);
}
