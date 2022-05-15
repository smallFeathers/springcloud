package com.xiaoyumao.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {

    @RequestMapping("/api/local")
    public String local() {
        System.out.println("在api gateway 中执行业务逻辑处理。。。");

        return "exec api gateway.";
    }
}
