package com.cloud.lesson.cloud.order.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class Orders {

    @Id
    private String id;
    @Column(name="order_num")
    private String orderNum;
    private String itemId;
}
