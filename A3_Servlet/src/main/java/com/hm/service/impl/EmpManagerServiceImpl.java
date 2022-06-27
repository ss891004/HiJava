package com.hm.service.impl;

import com.hm.dao.EmpManagerDao;
import com.hm.dao.impl.EmpManagerDaoImpl;
import com.hm.entity.EmpManager;
import com.hm.service.EmpManagerService;
import com.hm.utils.DbUtils;

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
