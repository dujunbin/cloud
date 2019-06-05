package com.cloud.lesson.cloud.order.service.impl;

import com.cloud.lesson.cloud.order.bo.OrderBo;
import com.cloud.lesson.cloud.order.client.ItemClient;
import com.cloud.lesson.cloud.order.config.IdWorkerConfig;
import com.cloud.lesson.cloud.order.domain.Orders;
import com.cloud.lesson.cloud.order.mapper.OrderMapper;
import com.cloud.lesson.cloud.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired(required = false)
    private OrderMapper orderMapper;

    @Autowired
    private IdWorkerConfig idWorkerConfig;

    @Autowired(required = false)
    private ItemClient itemClient;

    public Orders getInfoById(String id){
        return orderMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public Integer createOrder(OrderBo orderBo) {

        int row=0;
        //调用商品微服务减库存
        Map<String,Object> map= itemClient.decreaseStock(orderBo.getItemId(),orderBo.getNum());
        if(map.get("code").equals("000000")){
            throw new RuntimeException("扣减商品库存出现问题");
        }

        //减库存，创建订单（雪花算法）
        //创建订单对象
        Orders orders=new Orders();
        orders.setId(UUID.randomUUID().toString().replace("-","").toUpperCase());
        orders.setOrderNum(idWorkerConfig.idWorker().toString());
        orders.setItemId(orderBo.getItemId());

        //2,把订单信息插入数据库
        row = orderMapper.insert(orders);
        return row;
    }


}
