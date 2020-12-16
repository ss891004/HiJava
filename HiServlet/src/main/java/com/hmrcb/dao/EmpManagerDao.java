package com.hmrcb.dao;

import com.hmrcb.entity.EmpManager;

public interface EmpManagerDao {
    public EmpManager select(String username);
}
