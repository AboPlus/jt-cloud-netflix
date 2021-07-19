package com.abo.jt.order.service;

import com.abo.jt.pojo.Order;
import com.abo.jt.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Abo
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public Order getOrder(String orderId) {
        log.info("获取订单，orderId={}",orderId);
        // TODO: 远程调用商品，获取商品列表
        // TODO: 远程调用用户，获取用户信息
        Order order = new Order();
        order.setId(orderId);
        // order.setUser(用户);
        // order.setItems(商品);
        return order;
    }

    @Override
    public void addOrder(Order order) {
        log.info("保存订单：{}",order);
        // TODO: 远程调用商品，减少库存
        // TODO: 远程调用用户，增加积分
    }
}
