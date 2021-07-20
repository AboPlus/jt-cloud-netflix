package com.abo.jt.order.service;

import com.abo.jt.order.feign.ItemClient;
import com.abo.jt.order.feign.UserClient;
import com.abo.jt.pojo.Item;
import com.abo.jt.pojo.Order;
import com.abo.jt.pojo.User;
import com.abo.jt.service.OrderService;
import com.abo.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Abo
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ItemClient itemClient;
    @Autowired
    private UserClient userClient;

    @Override
    public Order getOrder(String orderId) {
        log.info("获取订单，orderId={}",orderId);
        // 远程调用商品，获取商品列表
        JsonResult<List<Item>> items = itemClient.getItems(orderId);
        // 远程调用用户，获取用户信息  (真实项目中获取已登录的userId)
        JsonResult<User> user = userClient.getUser(7);
        Order order = new Order();
        order.setId(orderId);
        order.setUser(user.getData());
        order.setItems(items.getData());
        return order;
    }

    @Override
    public void addOrder(Order order) {
        log.info("保存订单：{}",order);
        // 远程调用商品，减少库存
        itemClient.decreaseNumber(order.getItems());
        // 远程调用用户，增加积分
        userClient.addScore(order.getUser().getId(), 1000);
    }
}
