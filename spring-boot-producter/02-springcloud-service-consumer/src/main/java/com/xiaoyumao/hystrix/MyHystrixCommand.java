package com.xiaoyumao.hystrix;


import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;

/**
 * 自定义Hystrix请求
 */
public class MyHystrixCommand extends HystrixCommand<String> {

    private RestTemplate restTemplate;

    public MyHystrixCommand (Setter setter,RestTemplate restTemplate){
        super(setter);
        this.restTemplate = restTemplate;
    }
    @Override
    protected String run() throws Exception {
        //这个里边调用远程服务

        return restTemplate.getForEntity("http://01-springcloud-service-provider/service/user",String.class).getBody();
    }

    /**
     * 当远程服务超时、异常、不可用时，会触发该熔断方法
     * @return
     */
    @Override
    public String getFallback(){
        //实现服务器的熔断降级逻辑

        return "custom error";
    }
}
