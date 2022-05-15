package com.xiaoyumao.springcloudservicefeign.fallback;

import com.xiaoyumao.springcloudservicefeign.service.HelloSevice;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 提供熔断报错信息的类
 * 要继承FallbackFactory接口，当熔断后走该方法，对helloService中的方法进行熔断错误输出
 */
@Component
public class MyFallBackFactory implements FallbackFactory<HelloSevice> {

    @Override
    public HelloSevice create(Throwable throwable) {
        return new HelloSevice() {
            @Override
            public String hello() {
                return throwable.getMessage();
            }
        };
    }
}
