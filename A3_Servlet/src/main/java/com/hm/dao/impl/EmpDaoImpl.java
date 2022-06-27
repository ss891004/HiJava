package com.hm.dao.impl;

import com.hm.dao.EmpDao;
import com.hm.entity.Emp;
import com.hm.entity.Page;
import com.hm.utils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class EmpDaoImpl implements EmpDao {
    private final QueryRunner queryRunner = new QueryRunner();
    @Override
    public List<Emp> selectAll(Page page) {
        try {
            List<Emp> emps = queryRunner.query(DbUtils.getConnection(),"select * from emp limit ?,?",new BeanListHandler<Emp>(Emp.class),page.getStartRows(),page.getPageSize());
            return emps;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long selectCount() {
        try {
            long count = (long)queryRunner.query(DbUtils.getConnection(),"select count(*) from emp;",new ScalarHandler<>());
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        try {
            int result= queryRunner.update(DbUtils.getConnection(),"delete from emp where id=?",id);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int insert(Emp emp) {
        try {
            int result= queryRunner.update(DbUtils.getConnection(),"insert into emp(name,salary,age) values(?,?,?)",emp.getName(),emp.getSalary(),emp.getAge());
            System.out.println(result);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.printf(e.getMessage());
        }
        return 0;
    }

    @Override
    public Emp select(int id) {
        try {
            Emp emp = queryRunner.query(DbUtils.getConnection(),"select * from emp where id = ?",new BeanHandler<Emp>(Emp.class),id);
            return emp;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int update(Emp emp) {
        try {
            int result = queryRunner.update(DbUtils.getConnection(),"update emp set name=?,salary=?,age=? where id= ?",emp.getName(),emp.getSalary(),emp.getAge(),emp.getId());
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
