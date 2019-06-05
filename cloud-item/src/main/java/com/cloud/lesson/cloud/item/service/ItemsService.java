package com.cloud.lesson.cloud.item.service;

import com.cloud.lesson.cloud.item.bo.OrderBo;
import com.cloud.lesson.cloud.item.domain.Items;

public interface ItemsService {

    Integer decreaseStock(OrderBo orderBo);
}
