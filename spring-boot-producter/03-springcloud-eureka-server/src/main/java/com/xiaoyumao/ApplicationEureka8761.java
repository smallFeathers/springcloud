package com.xiaoyumao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //开启eureka服务中心注册端
public class ApplicationEureka8761 {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationEureka8761.class, args);
    }

}
