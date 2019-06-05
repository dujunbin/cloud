package com.cloud.lesson.cloud.item.service.impl;
import com.cloud.lesson.cloud.item.bo.OrderBo;
import com.cloud.lesson.cloud.item.domain.Items;
import com.cloud.lesson.cloud.item.mapper.ItemsMapper;
import com.cloud.lesson.cloud.item.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ItemsServiceImpl implements ItemsService {

    @Autowired(required = false)
    private ItemsMapper itemsMapper;

    @Override
    @Transactional
    public Integer decreaseStock(OrderBo orderBo) {
        // 判断扣减库存的商品是否存在
        Items items=itemsMapper.selectByPrimaryKey(orderBo.getItemId());
        if(items == null){
            throw new RuntimeException("商品不存在");
        }
        Integer counts=items.getCounts();
        if(counts - orderBo.getNum() <0 ){
            throw new RuntimeException("商品库存不足");
        }

        Items goods =new Items();
        goods.setCounts(100 - orderBo.getNum());
        goods.setId(orderBo.getItemId());
        int row=itemsMapper.updateByPrimaryKeySelective(goods);
        return row;
    }
}
