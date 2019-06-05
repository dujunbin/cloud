package com.cloud.lesson.cloud.order.controller;

import com.cloud.lesson.cloud.order.domain.Orders;
import com.cloud.lesson.cloud.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
@RefreshScope //刷新会随配置属性刷新
public class OrderController {

    @Value("${label:default}")
    private String label;

    @Autowired
    private OrderService orderService;

    @GetMapping("{id}")
    public Orders getInfoById(@PathVariable("id") String id){
        return orderService.getInfoById(id);
    }

    //测试远端配置发生变化后，配置的客户端是否能够刷新
    @GetMapping("label")
    public String getLabel(){
        return label;
    }

}
