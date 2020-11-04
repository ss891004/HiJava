package com.hmrcb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    /**
     *
     CREATE TABLE orders(
     order_id INT PRIMARY KEY AUTO_INCREMENT,
     order_no VARCHAR(20),
     order_price FLOAT
     );

     INSERT INTO orders(order_no, order_price) VALUES('aaaa', 23);
     INSERT INTO orders(order_no, order_price) VALUES('bbbb', 33);
     INSERT INTO orders(order_no, order_price) VALUES('cccc', 22);

     */

    //Order实体类中属性名和orders表中的字段名是不一样的
    private int id;
    //id===>order_id
    private String orderNo;
    //orderNo===>order_no
    private float price;
    //price===>order_price

}