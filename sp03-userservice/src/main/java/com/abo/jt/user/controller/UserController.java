package com.abo.jt.user.controller;

import com.abo.jt.pojo.User;
import com.abo.jt.service.UserService;
import com.abo.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Abo
 */
@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public JsonResult<User> getUser(@PathVariable Integer userId){
        log.info("get user,userId={}",userId);
        User user = userService.getUser(userId);
        return JsonResult.ok(user);
    }

    @GetMapping("/{userId}/score")
    public JsonResult addScore(@PathVariable Integer userId, Integer score){
        userService.addScore(userId, score);
        return JsonResult.ok();
    }

}
