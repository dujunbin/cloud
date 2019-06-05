package com.cloud.lesson.cloud.item.controller;

import com.cloud.lesson.cloud.item.bo.OrderBo;
import com.cloud.lesson.cloud.item.domain.Items;
import com.cloud.lesson.cloud.item.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("item")
public class ItemController {
    @Autowired
    private ItemsService itemsService;

    @PostMapping("{itemId}/{count}")
    public Map<String,Object> decreaseStock(
            @PathVariable("itemId") String itemId,
            @PathVariable("count") Integer count){
        OrderBo orderBo=new OrderBo();
        orderBo.setNum(count);
        orderBo.setItemId(itemId);

        Integer row=itemsService.decreaseStock(orderBo);
        Map<String,Object> map=new HashMap<>();
        map.put("code",row >0 ?"111111":"000000");
        map.put("message",row > 0 ?"success" : "fail");
        return  map;
    }
}
