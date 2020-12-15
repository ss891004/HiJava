package com.hmrcb.service.impl;

import com.hmrcb.dao.EmpDao;
import com.hmrcb.dao.impl.EmpDaoImpl;
import com.hmrcb.entity.Emp;
import com.hmrcb.entity.Page;
import com.hmrcb.service.EmpService;
import com.hmrcb.utils.DbUtils;

import java.util.List;

public class EmpServiceImpl implements EmpService {
    private EmpDao empDao = new EmpDaoImpl();
    @Override
    public List<Emp> showAllEmpByPage(Page page) {
        List<Emp> emps = null;
        try {
            DbUtils.begin();
            long count = empDao.selectCount();
            page.setTotalCounts((int)count);//赋值总条数，计算总页数
            emps = empDao.selectAll(page);
            DbUtils.commit();
        } catch (Exception e) {
            DbUtils.rollback();
            e.printStackTrace();
        }
        return emps;
    }

    @Override
    public int deleteEmp(int id) {
        int result = 0;
        try {
            DbUtils.begin();
            result = empDao.delete(id);
            DbUtils.commit();
        } catch (Exception e) {
            DbUtils.rollback();
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int addEmp(Emp emp) {
        int result = 0;
        try {
            DbUtils.begin();
            result = empDao.insert(emp);
            DbUtils.commit();
        } catch (Exception e) {
            DbUtils.rollback();
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Emp selectEmpById(int id) {
        Emp emp = null;
        try {
            DbUtils.begin();
            emp = empDao.select(id);
            DbUtils.commit();
        } catch (Exception e) {
            DbUtils.rollback();
            e.printStackTrace();
        }

        return emp;
    }

    @Override
    public int modifyEmp(Emp emp) {
        int result = 0;
        try {
            DbUtils.begin();
            result = empDao.update(emp);
            DbUtils.commit();
        } catch (Exception e) {
            DbUtils.rollback();
            e.printStackTrace();
        }
        return result;
    }
}
