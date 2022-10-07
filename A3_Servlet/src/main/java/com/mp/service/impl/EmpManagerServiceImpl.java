package com.mp.service.impl;

import com.mp.dao.EmpManagerDao;
import com.mp.dao.impl.EmpManagerDaoImpl;
import com.mp.entity.EmpManager;
import com.mp.service.EmpManagerService;
import com.mp.utils.DbUtils;

public class EmpManagerServiceImpl implements EmpManagerService {
    private final EmpManagerDao empManagerDao = new EmpManagerDaoImpl();
    @Override
    public EmpManager login(String username, String password) {
        EmpManager empManager = null;
        try {
            DbUtils.begin();
            EmpManager temp = empManagerDao.select(username);
            if(temp!=null){
                if(temp.getPassword().equals(password)){
                    empManager = temp;
                }
            }
            DbUtils.commit();
        } catch (Exception e) {
            DbUtils.rollback();
            e.printStackTrace();
        }
        return empManager;
    }
}
