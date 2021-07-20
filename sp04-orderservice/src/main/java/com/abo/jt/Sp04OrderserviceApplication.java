package com.abo.jt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @EnableFeignClients 启用Feign客户端，加上该注解项目启动时会扫描所有的@FeignClient
 * @author Abo
 */
@EnableFeignClients
@SpringBootApplication
public class Sp04OrderserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Sp04OrderserviceApplication.class, args);
    }

}
