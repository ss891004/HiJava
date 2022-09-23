package com.mybatis.mapper;

import com.mybatis.model.OrderModel;

public interface OrderMapper {

    // 当表查询
    OrderModel getById(int id);

    OrderModel getById2(int id);

    OrderModel getById3(int id);

    //一对一关联查询

    //使用到了级联赋值，多级之间用.进行引用，此处我们只有一级，可以有很多级。
    OrderModel getById4(int id);

    // 使用mapper xml中另外一个元素association，这个元素可以配置关联对象的映射关系
    OrderModel getById5(int id);

    //先按照订单id查询订单，然后通过订单记录中用户id去用户表查询用户信息，最终执行了2次查询。
    OrderModel getById6(int id);

    //传递多个参数
    OrderModel getById7(int id);
    
    //一对多查询(2种方式)
    OrderModel getById8(int id);

    OrderModel getById9(int id);

    OrderModel getById10(int id);

}

