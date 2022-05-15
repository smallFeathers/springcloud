package com.xiaoyumao.controller;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xiaoyumao.hystrix.MyHystrixCommand;
import com.xiaoyumao.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController

public class WebController {
    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping("/web/hello")
    public String hello(){
        //逻辑处理
        //调用springcloud提供的服务
//        ResponseEntity<User> forEntity = restTemplate.getForEntity("http://01-springcloud-service-provider/service/user/", User.class);
//        User body = forEntity.getBody();
//        HttpStatus statusCode = forEntity.getStatusCode();
//        HttpHeaders headers = forEntity.getHeaders();
//        int statusCodeValue = forEntity.getStatusCodeValue();
//
//        System.out.println("body: "+body);
//        System.out.println("statusCode: "+statusCode);
//        System.out.println("headers: "+headers);
//        System.out.println("statusCodeValue: "+statusCodeValue);


        return  restTemplate.getForEntity("http://01-springcloud-service-provider/service/user",String.class).getBody();
    }
    @RequestMapping("/web/hello2")
    public String hello2(){
        //逻辑处理
//        //调用springcloud提供的服务
//        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://01-springcloud-service-provider/service/hello/", String.class);
//        String body = forEntity.getBody();
//        HttpStatus statusCode = forEntity.getStatusCode();
//        HttpHeaders headers = forEntity.getHeaders();
//        int statusCodeValue = forEntity.getStatusCodeValue();
//
//        System.out.println("body: "+body);
//        System.out.println("statusCode: "+statusCode);
//        System.out.println("headers: "+headers);
//        System.out.println("statusCodeValue: "+statusCodeValue);


        return  restTemplate.getForEntity("http://01-springcloud-service-provider/service/hello",String.class).getBody();
    }

    @RequestMapping("/web/getUser")
    public String getUser(){
        //逻辑处理
//        //调用springcloud提供的服务 使用重载方法1
        // 可变参数控制的重载
//        String[] aarrays = {"12345","小羽毛3","13312312312"};
//        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://01-springcloud-service-provider/service/getUser?id={0}&name={1}&phone={2}", String.class, aarrays);
        // map控制的重载
        Map map = new HashMap();
        map.put("id",123123);
        map.put("name","小羽毛map");
        map.put("phone","13312312312");
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://01-springcloud-service-provider/service/getUser?id={id}&name={name}&phone={phone}", String.class, map);
//        String body = forEntity.getBody();
//        HttpStatus statusCode = forEntity.getStatusCode();
//        HttpHeaders headers = forEntity.getHeaders();
//        int statusCodeValue = forEntity.getStatusCodeValue();
//
//        System.out.println("body: "+body);
//        System.out.println("statusCode: "+statusCode);
//        System.out.println("headers: "+headers);
//        System.out.println("statusCodeValue: "+statusCodeValue);

        return forEntity.getBody();
//        return  restTemplate.getForEntity("http://01-springcloud-service-provider/service/hello",String.class).getBody();
    }

    @RequestMapping("/web/addUser")
    public String addUser(){
        //逻辑处理
//        //调用springcloud提供的服务 使用重载方法1
        MultiValueMap<String,Object> dataMap = new LinkedMultiValueMap<String,Object>();
        dataMap.add("id","123456");
        dataMap.add("name","小羽毛Multi");
        dataMap.add("phone","12312312312");

        ResponseEntity<String> forEntity = restTemplate.postForEntity("http://01-springcloud-service-provider/service/addUser",dataMap, String.class);
//        String body = forEntity.getBody();
//        HttpStatus statusCode = forEntity.getStatusCode();
//        HttpHeaders headers = forEntity.getHeaders();
//        int statusCodeValue = forEntity.getStatusCodeValue();
//
//        System.out.println("body: "+body);
//        System.out.println("statusCode: "+statusCode);
//        System.out.println("headers: "+headers);
//        System.out.println("statusCodeValue: "+statusCodeValue);

        return forEntity.getBody();
//        return  restTemplate.getForEntity("http://01-springcloud-service-provider/service/hello",String.class).getBody();
    }

    @RequestMapping ("/web/updateUser")
    public String updateUser(){
        //逻辑处理
//        //调用springcloud提供的服务 使用重载方法1
        MultiValueMap<String,Object> dataMap = new LinkedMultiValueMap<String,Object>();
        dataMap.add("id","123456");
        dataMap.add("name","小羽毛Multi");
        dataMap.add("phone","12312312312");

        //使用put时，url不要带参数拼接，和新增一样
        restTemplate.put("http://01-springcloud-service-provider/service/updateUser",dataMap);
        restTemplate.put("http://01-springcloud-service-provider/service/updateUser",dataMap);

        return "update success";
    }

    @RequestMapping("/web/deleteUser")
    public String deleteUser(){
        //逻辑处理
//        //调用springcloud提供的服务 使用重载方法1
//        MultiValueMap<String,Object> dataMap = new LinkedMultiValueMap<String,Object>();
//        dataMap.add("id","123456");
//        dataMap.add("name","小羽毛Multi");
//        dataMap.add("phone","12312312312");
        Map dataMap = new HashMap();
        dataMap.put("id","12345");
        dataMap.put("name","小羽毛1");
        dataMap.put("phone","13312312312");

        //使用delete时，url要带参数拼接，和get 一样
        restTemplate.delete("http://01-springcloud-service-provider/service/deleteUser?id={id}&name={name}&phone={phone}",dataMap);
        restTemplate.delete("http://01-springcloud-service-provider/service/deleteUser?id={id}&name={name}&phone={phone}",dataMap);

        return "delete success";
    }

    @RequestMapping("/web/hystrix")
//    @HystrixCommand(fallbackMethod = "error")   //熔断后执行的操作（调用回掉方法）

    //对熔断进行时间设置，设置为3.5秒(单位为毫秒)
    @HystrixCommand(fallbackMethod = "error",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="3500")
    })   //熔断后执行的操作（调用回掉方法）

    //ignoreExceptions 是用来忽略异常进而不执行熔断的配置
//    @HystrixCommand(fallbackMethod = "error",ignoreExceptions = RuntimeException.class)
    public String hystrix(){
//        int a = 10/0;
        return  restTemplate.getForEntity("http://01-springcloud-service-provider/service/user",String.class).getBody();
    }


    /**
     * 使用自定义的hystrix调用服务
     * @return
     */
    @RequestMapping("/web/hystrix2")
    public String hystrix2() throws ExecutionException, InterruptedException {
        MyHystrixCommand myHystrixCommand = new MyHystrixCommand(com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")),restTemplate);
        //同步调用
//        String execute = myHystrixCommand.execute();

        //1、异步调用
        Future<String> queue = myHystrixCommand.queue();

        //2、中间执行其他逻辑
        System.out.println("异步执行1");

        //3、拿到返回的结果（阻塞方法直到拿到结果）
        String s = queue.get();
        System.out.println("异步执行2");
//        return execute;
        return s;
    }
    public String error(Throwable throwable){
        System.out.println("异常"+throwable.getMessage());
        return "error";
    }

    /**
     * 测试仪表盘使用的方法
     *
     * 注意：如果再调用的方法上有形参，那么fallback方法也同样需要形参；不然会报 找不到fallback方法异常
     * 设置服务熔断的核心配置：
     * 1、启用断路器 @HystrixProperty(name = "circuitBreaker.enabled", value = "true")
     * 2、窗口期内触发断路的请求阀值（默认20） @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10")
     * 3、设置时间窗口期（默认5秒） @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000")
     * 4、窗口期内错误百分比（默认50%） @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
     * 如上设置的值，如果在10秒内，失败率达到请求次数（10）的百分之60，也就是6次就会 打开断路器；否则断路器依然关闭
     *  默认情况下，在一个窗口期内（closed状态），若请求数超过20，并且错误的数量超过50%，那么就会触发熔断。在接下来的一个窗口期内任何请求都会降级（开启状态），
     *  直到下一个请求过去进入半开状态，进入半开状态后继续从新判定。
     *
     * 当断路器开启时，所有的请求都不会转发，而是直接调用fallback方法；在一段时间后（默认5秒），断路器处于半开状态，尝试将请求转发，
     * 如果得到正确的响应。则将断路器关闭，恢复正常调用；否则断路器状态再次打开，重新计时后再进入半开状态
     *
     * @return
     */
    @RequestMapping("/web/hystrix3")
    //对熔断进行时间设置，设置为3.5秒(单位为毫秒)
    @HystrixCommand(fallbackMethod = "error",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
    })   //熔断后执行的操作（调用回掉方法）
    public String hystrix3(){
//        int a = 10/0;
        return  restTemplate.getForEntity("http://01-springcloud-service-provider/service/user",String.class).getBody();
    }

}
