package com.abo.jt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @author Abo
 */
@EnableTurbine
@SpringBootApplication
public class Sp08TurbineApplication {

    public static void main(String[] args) {
        SpringApplication.run(Sp08TurbineApplication.class, args);
    }

}
