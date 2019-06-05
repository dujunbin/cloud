package com.cloud.lesson.cloud.order.service;

import com.cloud.lesson.cloud.order.bo.OrderBo;
import com.cloud.lesson.cloud.order.domain.Orders;

public interface OrderService {

    Orders getInfoById(String id);

    Integer createOrder(OrderBo orderBo);
}
