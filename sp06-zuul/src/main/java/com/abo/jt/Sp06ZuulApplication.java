package com.abo.jt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @EnableZuulProxy 自动启动Zuul代理
 * @author Abo
 */
@EnableZuulProxy
@SpringBootApplication
public class Sp06ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(Sp06ZuulApplication.class, args);
    }

}
