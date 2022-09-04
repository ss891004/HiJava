package com.hm.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * create table account(
 * id int primary key auto_increment,
 * name varchar(40),
 * money float
 * );
 * <p>
 * insert into account(name,money) values('A',1000);
 * insert into account(name,money) values('B',1000);
 * insert into account(name,money) values('C',1000);
 */
//事务范例
public class J12Tx {

    /**
     * @Method: testTransaction1
     * @Description: 模拟转账成功时的业务场景
     */
    public void testTransaction1() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = J02.getConnection();
            conn.setAutoCommit(false);
            //通知数据库开启事务(start transaction)
            String sql1 = "update account set money=money-100 where name='A'";
            st = conn.prepareStatement(sql1);
            st.executeUpdate();
            String sql2 = "update account set money=money+100 where name='B'";
            st = conn.prepareStatement(sql2);
            st.executeUpdate();
            conn.commit();//上面的两条SQL执行Update语句成功之后就通知数据库提交事务(commit)
            System.out.println("成功！！！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            J02.release(conn, st, rs);
        }
    }

    /**
     * @Method: testTransaction2
     * @Description: 模拟转账过程中出现异常导致有一部分SQL执行失败后让数据库自动回滚事务
     */
    public void testTransaction2() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = J02.getConnection();
            conn.setAutoCommit(false);
            //通知数据库开启事务(start transaction)
            String sql1 = "update account set money=money-100 where name='A'";
            st = conn.prepareStatement(sql1);
            st.executeUpdate();
            //用这句代码模拟执行完SQL1之后程序出现了异常而导致后面的SQL无法正常执行，事务也无法正常提交，此时数据库会自动执行回滚操作
            int x = 1 / 0;
            String sql2 = "update account set money=money+100 where name='B'";
            st = conn.prepareStatement(sql2);
            st.executeUpdate();
            conn.commit();//上面的两条SQL执行Update语句成功之后就通知数据库提交事务(commit)
            System.out.println("成功！！！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            J02.release(conn, st, rs);
        }
    }

    /**
     * @Method: testTransaction3
     * @Description: 模拟转账过程中出现异常导致有一部分SQL执行失败时手动通知数据库回滚事务
     */
    public void testTransaction3() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = J02.getConnection();
            conn.setAutoCommit(false);
            //通知数据库开启事务(start transaction)
            String sql1 = "update account set money=money-100 where name='A'";
            st = conn.prepareStatement(sql1);
            st.executeUpdate();
            //用这句代码模拟执行完SQL1之后程序出现了异常而导致后面的SQL无法正常执行，事务也无法正常提交
            int x = 1 / 0;
            String sql2 = "update account set money=money+100 where name='B'";
            st = conn.prepareStatement(sql2);
            st.executeUpdate();
            conn.commit();//上面的两条SQL执行Update语句成功之后就通知数据库提交事务(commit)
            System.out.println("成功！！！");
        } catch (Exception e) {
            try {
                //捕获到异常之后手动通知数据库执行回滚事务的操作
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            J02.release(conn, st, rs);
        }
    }
}
