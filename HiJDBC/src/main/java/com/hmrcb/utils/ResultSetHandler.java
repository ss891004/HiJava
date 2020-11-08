package com.hmrcb.utils;

import java.sql.ResultSet;

/**
 * @ClassName: ResultSetHandler
 * @Description:结果集处理器接口
 *
 */
public interface ResultSetHandler {

    /**
     * @Method: handler
     * @Description: 结果集处理方法
     *
     * @param rs 查询结果集
     * @return
     */
    public Object handler(ResultSet rs);
}