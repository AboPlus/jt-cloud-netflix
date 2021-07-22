package com.abo.jt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @EnableHystrixDashboard 启动HystrixDashboard仪表盘
 * @author Abo
 */
@EnableHystrixDashboard
@SpringBootApplication
public class Sp07HystrixDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(Sp07HystrixDashboardApplication.class, args);
    }

}