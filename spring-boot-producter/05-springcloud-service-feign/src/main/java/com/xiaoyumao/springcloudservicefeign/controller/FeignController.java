package com.xiaoyumao.springcloudservicefeign.controller;

import com.xiaoyumao.springcloudservicefeign.service.HelloSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {
    @Autowired
    private HelloSevice helloSevice;

    @RequestMapping("/web/hello")
    public String hello(){
        String hello = helloSevice.hello();
        return hello;
    }
}
