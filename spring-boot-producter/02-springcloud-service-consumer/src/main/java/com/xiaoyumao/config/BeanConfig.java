package com.xiaoyumao.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration  //该注解是相当于配置一个spring applicationContext.xml配置

public class BeanConfig {

    /*
    @Bean 等价于在xml文件中配置：
    <bean id="restTemplate" class="xxx.xxx.xxx.RestTemplate">
    </bean>
     */
    @LoadBalanced //ribbon里边的负载均衡，加上这个注解，就相当于ribbon调用微服务了
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /**
     * 覆盖掉ribbon 原来的默认的轮询负载均衡策略
     *
     * ribbon7中主要负载均衡策略
     * @return
     */
    @Bean
    public IRule irule(){
//        return new RandomRule();    //采用随机的负载均衡策略
        return new RetryRule();    //采用随机的负载均衡策略
    }
}
