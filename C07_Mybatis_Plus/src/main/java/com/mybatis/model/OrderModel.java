package com.mybatis.model;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Alias("order")
public class OrderModel {
    private Long id;
    private Long userId;
    private Long createTime;
    private Long upTime;

    //订单表和用户表的一对一的关系
    private UserModel2 userModel;

    //一对多查询
    private List<OrderDetailModel> xxx;
}

/*
DROP TABLE IF EXISTS t_order;
CREATE TABLE t_order(
  id int AUTO_INCREMENT PRIMARY KEY COMMENT '订单id',
  user_id INT NOT NULL DEFAULT 0 COMMENT '用户id，来源于t_user.id',
  create_time BIGINT NOT NULL DEFAULT 0 COMMENT '订单创建时间(时间戳，秒)',
  up_time BIGINT NOT NULL DEFAULT 0 COMMENT '订单最后修改时间(时间戳，秒)'
) COMMENT '订单表';
INSERT INTO t_order VALUES (1,2,unix_timestamp(now()),unix_timestamp(now())),(2,1,unix_timestamp(now()),unix_timestamp(now()));
* */