package com.xiaoyumao.springcloudservicefeign.fallback;

import com.xiaoyumao.springcloudservicefeign.service.HelloSevice;
import org.springframework.stereotype.Component;

@Component  //把这个类交给springbean管理
public class MyFallBack implements HelloSevice {

    /**
     * 这里要重写接口中的方法，因为当远程服务掉不通这个方法的时候就回来本地的回调类中找这个方法，这样就实现降级服务了
     * @return
     */
    @Override
    public String hello() {
        return "这是本地降级方法。。。";
    }
}
