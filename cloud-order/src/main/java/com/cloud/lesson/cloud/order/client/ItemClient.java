package com.cloud.lesson.cloud.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@FeignClient(name="item")
public interface ItemClient {

    @PostMapping("item/{itemId}/{count}")
    public Map<String,Object> decreaseStock(
            @PathVariable("itemId") String itemId,
            @PathVariable("count") Integer count);
}
