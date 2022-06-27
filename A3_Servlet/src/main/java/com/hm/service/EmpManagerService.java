package com.hm.service;

import com.hm.entity.EmpManager;

public interface EmpManagerService {
    EmpManager login(String username, String password);
}
