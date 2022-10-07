package com.mp.service;

import com.mp.entity.EmpManager;

public interface EmpManagerService {
    EmpManager login(String username, String password);
}
