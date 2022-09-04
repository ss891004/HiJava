package com.hm.jdbc;

import java.sql.*;


/**
create table account(
id int primary key auto_increment,
name varchar(40),
money float
);

insert into account(name,money) values('A',1000);
insert into account(name,money) values('B',1000);
insert into account(name,money) values('C',1000);
 */
public class J13Tx {

    //设置事务回滚点
    public void testTransaction1() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Savepoint sp = null;

        try {
            conn = J02.getConnection();
            conn.setAutoCommit(false);

            String sql1 = "update account set money=money-100 where name='A'";
            st = conn.prepareStatement(sql1);
            st.executeUpdate();

            //设置事务回滚点
            sp = conn.setSavepoint();

            String sql2 = "update account set money=money+100 where name='B'";
            st = conn.prepareStatement(sql2);
            st.executeUpdate();

            //程序执行到这里出现异常，后面的sql3语句执行将会中断
            int x = 1 / 0;

            String sql3 = "update account set money=money+100 where name='C'";
            st = conn.prepareStatement(sql3);
            st.executeUpdate();

            conn.commit();

        } catch (Exception e) {
            try {
                /**
                 * 我们在上面向数据库发送了3条update语句，
                 * sql3语句由于程序出现异常导致无法正常执行，数据库事务而已无法正常提交，
                 * 由于设置的事务回滚点是在sql1语句正常执行完成之后，sql2语句正常执行之前,
                 * 那么通知数据库回滚事务时，不会回滚sql1执行的update操作
                 * 只会回滚到sql2执行的update操作，也就是说，上面的三条update语句中，sql1这条语句的修改操作起作用了
                 * sql2的修改操作由于事务回滚没有起作用，sql3由于程序异常没有机会执行
                 */
                //回滚到设置的事务回滚点
                conn.rollback(sp);
                //回滚了要记得通知数据库提交事务
                conn.commit();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            J02.release(conn, st, rs);
        }
    }
}