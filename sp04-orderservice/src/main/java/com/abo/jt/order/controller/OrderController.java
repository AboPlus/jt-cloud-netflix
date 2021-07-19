package com.abo.jt.order.controller;

import com.abo.jt.pojo.Item;
import com.abo.jt.pojo.Order;
import com.abo.jt.pojo.User;
import com.abo.jt.service.OrderService;
import com.abo.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author Abo
 */
@Slf4j
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/{orderId}")
    public JsonResult<Order> getOrder(@PathVariable String orderId){
        log.info("get order,id={}",orderId);
        Order order = orderService.getOrder(orderId);
        return JsonResult.ok(order);
    }

    @GetMapping("/add")
    public JsonResult addOrder(){
        //模拟post提交的数据
        Order order = new Order();
        User user = new User(7, null, null);
        Item item1 = new Item(1,"aaa",2);
        Item item2 = new Item(2,"bbb",1);
        Item item3 = new Item(3,"ccc",3);
        Item item4 = new Item(4,"ddd",1);
        Item item5 = new Item(5,"eee",5);
        Item[] item = {item1, item2, item3, item4, item5};
        List<Item> items = Arrays.asList(item);
        order.setId("123abc").setUser(user).setItems(items);

        orderService.addOrder(order);
        return JsonResult.ok();
    }

}
