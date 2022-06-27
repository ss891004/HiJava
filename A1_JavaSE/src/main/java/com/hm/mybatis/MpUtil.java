package com.hm.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public class MpUtil {

    //SqlSessionFactory对象的生命周期一般和应用的生命周期是一样的，随着应用的启动而创建，随着应用的停止而结束，所以一般是一个全局对象，一般情况下一个db对应一个SqlSessionFactory对象
    private static final SqlSessionFactory sqlSessionFactory = build();

    public static SqlSessionFactory build() {
        try {
            //指定mybatis全局配置文件
            //读取全局配置文件
            //构建SqlSessionFactory对象
            return new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FunctionalInterface
    public interface SessionCall<O> {
        O call(SqlSession session) throws Exception;
    }

    @FunctionalInterface
    public interface MapperCall<T, O> {
        O call(T mapper) throws Exception;
    }

    public static <T, O> O callMapper(Class<T> tClass, MapperCall<T, O> mapper) throws Exception {
        return call(session -> mapper.call(session.getMapper(tClass)));
    }

    public static <O> O call(SessionCall<O> sessionCall) throws Exception {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            return sessionCall.call(session);
        }
    }
}