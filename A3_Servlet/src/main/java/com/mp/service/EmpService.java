package com.mp.service;

import com.mp.entity.Emp;
import com.mp.entity.Page;

import java.util.List;

public interface EmpService {
    List<Emp> showAllEmpByPage(Page page);
    int deleteEmp(int id);
    int addEmp(Emp emp);
    Emp selectEmpById(int id);
    int modifyEmp(Emp emp);
}
