package com.hm.service;

import com.hm.entity.Emp;
import com.hm.entity.Page;

import java.util.List;

public interface EmpService {
    List<Emp> showAllEmpByPage(Page page);
    int deleteEmp(int id);
    int addEmp(Emp emp);
    Emp selectEmpById(int id);
    int modifyEmp(Emp emp);
}
