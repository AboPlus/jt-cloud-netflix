package com.abo.jt.item.controller;

import com.abo.jt.pojo.Item;
import com.abo.jt.service.ItemService;
import com.abo.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Abo
 */
@Slf4j
@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Value("${server.port}")
    private int port;

    /**
     * JsonResult - 响应结构的封装对象
     *      - code：响应的状态码，可以使http状态，或者也可以自己任意定义
     *      - msg ：提示消息
     *      - data：发回的响应数据对象
     * @param orderId
     * @return
     */
    @GetMapping("/{orderId}")
    public JsonResult<List<Item>> getItems(@PathVariable String orderId){
        log.info("server.port is {},and orderId is {}",port,orderId);
        List<Item> items = itemService.getItems(orderId);
        return JsonResult.ok(items).msg("port= "+port);
    }

    /**
     * 减少商品库存
     * 客户端发送的请求协议，商品集合要包含在请求协议体中
     * @RequestBody 是完整接收请求协议体数据
     *
     * 协议头：
     * http 1.1 /decreaseNumber
     * aaa: aaa
     * bbb: bbb
     *
     * 协议体
     * [{id:23, name:xxx, number:2},{..., ..., ...},{..., ..., ...}]
     *
     * @param items
     * @return
     */
    @PostMapping("/decreaseNumber")
    public JsonResult decreaseNumber(@RequestBody List<Item> items){
        itemService.decreaseNumbers(items);
        return JsonResult.ok();
    }

}
