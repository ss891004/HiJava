package com.mybatis.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GoodsModel {
    private Long id;
    private String name;
    private Double price;
}


/*
DROP TABLE IF EXISTS t_goods;
CREATE TABLE t_goods(
  id int AUTO_INCREMENT PRIMARY KEY COMMENT '商品id',
  name VARCHAR(32) NOT NULL DEFAULT '' COMMENT '商品名称',
  price DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '商品价格'
) COMMENT '商品信息表';
INSERT INTO t_goods VALUES (1,'Mybatis系列',8.88),(2,'maven高手系列',16.66);
* */