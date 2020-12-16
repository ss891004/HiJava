package com.hmrcb.service.impl;

import com.hmrcb.dao.EmpManagerDao;
import com.hmrcb.dao.impl.EmpManagerDaoImpl;
import com.hmrcb.entity.EmpManager;
import com.hmrcb.service.EmpManagerService;
import com.hmrcb.utils.DbUtils;

public class EmpManagerServiceImpl implements EmpManagerService {
    private EmpManagerDao empManagerDao = new EmpManagerDaoImpl();
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
