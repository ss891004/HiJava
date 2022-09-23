package com.mybatis.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderDetailModel {

    private int id;
    private int orderId;
    private int goodsId;
    private int num;
    private Double totalPrice;

}


/*
* DROP TABLE IF EXISTS t_order_detail;
CREATE TABLE t_order_detail(
  id int AUTO_INCREMENT PRIMARY KEY COMMENT '订单明细id',
  order_id INT NOT NULL DEFAULT 0 COMMENT '订单id，来源于t_order.id',
  goods_id INT NOT NULL DEFAULT 0 COMMENT '商品id，来源于t_goods.id',
  num INT NOT NULL DEFAULT 0 COMMENT '商品数量',
  total_price DECIMAL(12,2) NOT NULL DEFAULT 0 COMMENT '商品总金额'
) COMMENT '订单表';
INSERT INTO t_order_detail VALUES (1,1,1,2,17.76),(2,1,1,1,16.66),(3,2,1,1,8.88);
* */