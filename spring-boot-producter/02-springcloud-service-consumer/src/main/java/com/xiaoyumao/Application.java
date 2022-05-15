package com.xiaoyumao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
//@EnableEurekaClient     //开启eureka客户端
//@EnableCircuitBreaker   //开启熔断器

@SpringCloudApplication //此注解相当于上边三个注解

//其中@EnableDiscoveryClient 和 @EnableEurekaClient 是相似的，前边这个注解可以适用所有注册中心
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
