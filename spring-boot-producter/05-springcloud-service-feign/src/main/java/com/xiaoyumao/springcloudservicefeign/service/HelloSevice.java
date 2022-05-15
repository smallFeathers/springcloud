package com.xiaoyumao.springcloudservicefeign.service;

import com.xiaoyumao.springcloudservicefeign.fallback.MyFallBack;
import com.xiaoyumao.springcloudservicefeign.fallback.MyFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

//@FeignClient("01-springcloud-service-provider") //通过feign的客户端注解，指定服务提供者的名字，进行绑定
@FeignClient(name="01-springcloud-service-provider",fallback = MyFallBack.class) //这地方当远程服务不可调用后，回调MyFallBack类中的方法
//@FeignClient(name="01-springcloud-service-provider",fallbackFactory = MyFallBackFactory.class) //可以将熔断错误信息返回的设置
public interface HelloSevice {

    /**
     * 声明式方法，具体在远程的服务提供者上边实现。声明式方法，可以在本地controller中进行调用
     *
     * @return
     */
    @RequestMapping("/service/hello")
    public String hello();
}
