package com.abo.jt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author Abo
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {
    private static final long serialVersionUID = 4346629880643904286L;
    /**
     * 订单id，String类型
     * 订单ID一般都是按照一定规则生成，所以订单ID使用String类型
     */
    private String id;
    /** 订单所属用户 */
    private User user;
    /** 订单中所包含的商品列表 */
    private List<Item> items;
}
