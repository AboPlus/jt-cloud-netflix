package com.abo.jt.user.service;

import com.abo.jt.pojo.User;
import com.abo.jt.service.UserService;
import com.abo.web.util.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Abo
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Value("${sp.user-service.users}")
    private String userJson;

    /**
     * 从demo数据中查询，如果demo数据中没有，返回一个固定值
     * @param id
     * @return
     */
    @Override
    public User getUser(Integer id) {
        log.info("users json string is {}",userJson);
        /*
        * 将userJson 转换为 List<User>
        * import com.fasterxml.jackson.core.type.TypeReference;
        * 利用匿名内部类的继承语法，写泛型类型参数<List<User>>
        * */
        List<User> list = JsonUtil.from(userJson, new TypeReference<List<User>>() {});
        for (User user : list) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        //假如上面没有找到用户，这里返回一个写死的用户数据
        return new User(id,"name-"+id,"pwd-"+id);
    }

    /**
     * 增加用户积分
     * @param id
     * @param score
     */
    @Override
    public void addScore(Integer id, Integer score) {
        log.info("user "+id+" - 增加积分 "+score);
    }
}
