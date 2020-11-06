package com.hmrcb;

import com.hmrcb.entity.User;
import com.hmrcb.utils.MyBatisUtil2;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;

//一级缓存
public class TestOneLevelCache {

    /*
     * 一级缓存: 也就Session级的缓存(默认开启)
     */
    @Test
    public void testCache1() {
        SqlSession session = MyBatisUtil2.getSqlSession();
        String statement = "com.hmrcb.mapper.UserMapper.queryUserById";
        User user = session.selectOne(statement, 4);
        System.out.println(user);

        /*
         * 一级缓存默认就会被使用
         */
        user = session.selectOne(statement, 4);
        System.out.println(user);
        session.close();
        /*
         1. 必须是同一个Session,如果session对象已经close()过了就不可能用了
         */
        session = MyBatisUtil2.getSqlSession();
        user = session.selectOne(statement, 4);
        System.out.println(user);

        /*
         2. 查询条件是一样的
         */
        user = session.selectOne(statement, 7);
        System.out.println(user);

        /*
         3. 没有执行过session.clearCache()清理缓存
         */
        //session.clearCache();
        user = session.selectOne(statement, 7);
        System.out.println(user);

        /*
         4. 没有执行过增删改的操作(这些操作都会清理缓存)
         */
        session.update("com.hmrcb.mapper.UserMapper.updateUser",
                new User(5, "shine_222", "asdfasdfasdf", true, new Date()));
        user = session.selectOne(statement, 2);
        System.out.println(user);
    }
}
