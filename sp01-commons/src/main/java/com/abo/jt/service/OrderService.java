package com.abo.jt.service;

import com.abo.jt.pojo.Order;

/**
 * @author Abo
 */
public interface OrderService {
    /**
     * 基于订单Id获取订单
     * @param orderId
     * @return
     */
    Order getOrder(String orderId);

    /**
     * 新增订单信息
     * @param order
     */
    void addOrder(Order order);

}
