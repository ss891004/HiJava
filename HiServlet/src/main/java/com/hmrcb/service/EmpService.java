package com.hmrcb.service;

import com.hmrcb.entity.Emp;
import com.hmrcb.entity.Page;

import java.util.List;

public interface EmpService {
    public List<Emp> showAllEmpByPage(Page page);
    public int deleteEmp(int id);
    public int addEmp(Emp emp);
    public Emp selectEmpById(int id);
    public int modifyEmp(Emp emp);
}
