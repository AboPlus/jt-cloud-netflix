package com.abo.jt.service;

import com.abo.jt.pojo.Item;

import java.util.List;

/**
 * @author Abo
 */
public interface ItemService {
    /**
     * 基于订单ID查询商品列表,注意参数中的订单id是String类型
     * @param orderId
     * @return
     */
    List<Item> getItems(String orderId);

    /**
     * 减少商品库存
     * @param items
     */
    void decreaseNumbers(List<Item> items);

}
