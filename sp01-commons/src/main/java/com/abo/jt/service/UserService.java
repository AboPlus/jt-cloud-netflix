package com.abo.jt.service;

import com.abo.jt.pojo.User;

/**
 * @author Abo
 */
public interface UserService {
    /**
     * 基于id查询用户
     * @param id
     * @return
     */
    User getUser(Integer id);

    /**
     * 增加用户积分
     * @param id
     * @param score
     */
    void addScore(Integer id, Integer score);
}
