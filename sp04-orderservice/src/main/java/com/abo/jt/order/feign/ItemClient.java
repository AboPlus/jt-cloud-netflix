package com.abo.jt.order.feign;

import com.abo.jt.pojo.Item;
import com.abo.web.util.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 通过这个接口，调用远程的商品服务
 * 通过注解配置：
 *      1.调用哪个服务
 *      2.调用服务的哪个路径
 *      3.向这个路径提交什么参数
 * @FeignClient(name = "name") -- 调用哪个服务
 * @author Abo
 */
@FeignClient(name = "item-service")
public interface ItemClient {
    /**
     * 获取商品列表
     * @GetMapping("/{orderId}") -- 调用服务的哪个路径
     * @PathVariable String orderId -- 向这个路径提交什么参数
      * @param orderId
     * @return
     */
    @GetMapping("/{orderId}")
    JsonResult<List<Item>> getItems(@PathVariable String orderId);

    /**
     * 减少商品库存
     * @param items
     * @return
     */
    @PostMapping("/decreaseNumber")
    JsonResult<?> decreaseNumber(@RequestBody List<Item> items);

}
