package com.demo.jdbc.utils;


import java.sql.SQLException;
import java.util.List;

//针对单个实体对象CRUD操作
public class AccountDao {

    public void add(Account account) throws SQLException{
        String sql = "insert into account(name,money) values(?,?)";
        Object[] params = {account.getName(),account.getMoney()};
        JdbcUtils.update(sql, params);
    }


    public void delete(int id) throws SQLException{
        String sql = "delete from account where id=?";
        Object[] params = {id};
        JdbcUtils.update(sql, params);
    }

    public void update(Account account) throws SQLException{

        String sql = "update account set name=?,money=? where id=?";
        Object[] params = {account.getName(),account.getMoney(),account.getId()};
        JdbcUtils.update(sql, params);

    }

    public Account find(int id) throws SQLException{
        String sql = "select * from account where id=?";
        Object[] params = {id};
        return (Account) JdbcUtils.query(sql, params, new BeanHandler(Account.class));
    }

    public List<Account> getAll() throws SQLException{
        String sql = "select * from account";
        Object[] params = {};
        return (List<Account>) JdbcUtils.query(sql, params,new BeanListHandler(Account.class));
    }
}