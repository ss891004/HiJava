package com.mp.dao;

import com.mp.entity.Emp;
import com.mp.entity.Page;

import java.util.List;

public interface EmpDao {
    List<Emp> selectAll(Page page);
    long selectCount();
    int delete(int id);
    int insert(Emp emp);
    Emp select(int id);
    int update(Emp emp);
}
