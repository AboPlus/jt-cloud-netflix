package com.abo.jt.order.feign;

import com.abo.jt.pojo.User;
import com.abo.web.util.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 通过这个接口，调用远程的用户服务
 * @author Abo
 */
@FeignClient(name = "user-service")
public interface UserClient {
    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    JsonResult<User> getUser(@PathVariable Integer userId);

    /**
     * 增加用户积分
     * @RequestParam("score") 在controller中可以省略，
     * 在feign调用接口中省略可能会有问题(绝大情况下依旧可以省略)
     * @param userId
     * @param score
     * @return
     */
    @GetMapping("/{userId}/score")
    JsonResult addScore(@PathVariable Integer userId,
                        @RequestParam("score") Integer score);
}
