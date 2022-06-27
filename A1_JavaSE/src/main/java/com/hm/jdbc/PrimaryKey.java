package com.hm.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
    create table test1
 (
       id int primary key auto_increment,
       name varchar(20)
  );
 */
//获得MySQL数据库自动生成的主键
public class PrimaryKey {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "insert into test1(name) values(?)";
            st = conn.prepareStatement(sql);
            st.setString(1, "aaa1");
            st.executeUpdate();
            //获取数据库自动生成的主键
            rs = st.getGeneratedKeys();
            if (rs.next()) {
                System.out.println(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}