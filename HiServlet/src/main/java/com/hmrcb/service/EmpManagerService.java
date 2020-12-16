package com.hmrcb.service;

import com.hmrcb.entity.EmpManager;

public interface EmpManagerService {
    public EmpManager login(String username, String password);
}
