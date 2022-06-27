package com.hm.dao;

import com.hm.entity.Emp;
import com.hm.entity.Page;

import java.util.List;

public interface EmpDao {
    List<Emp> selectAll(Page page);
    long selectCount();
    int delete(int id);
    int insert(Emp emp);
    Emp select(int id);
    int update(Emp emp);
}
