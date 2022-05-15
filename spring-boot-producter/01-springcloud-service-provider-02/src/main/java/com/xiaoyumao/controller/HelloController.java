package com.xiaoyumao.controller;

import com.xiaoyumao.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @RequestMapping("/service/hello")
    public String hello(){
        //业务处理逻辑
        System.out.println("服务提供者2.。。。。。。。");
        return  "Hello, Spring Cloud. provider2";
    }

    @RequestMapping("/service/user")
    public User user(){
        //业务处理逻辑
        System.out.println("服务提供者user2.。。。。。。。");
        User user = new User();
        user.setId(1101);
        user.setName("小羽毛2");
        user.setPhone("13312312312");
        return  user;
    }
    @RequestMapping("/service/getUser")
    public User getUser(@RequestParam("id") int id,
                        @RequestParam("name") String name,
                        @RequestParam("phone") String phone){
        //业务处理逻辑
        System.out.println("服务提供者user2.。。。。。。。");
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPhone(phone);
        return  user;
    }
    @PutMapping("/service/updateUser")
    public User updateUser(@RequestParam("id") int id,
                           @RequestParam("name") String name,
                           @RequestParam("phone") String phone){
        //业务处理逻辑
        System.out.println("服务提供者updateuser2.。。。。。。。");
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPhone(phone);
        return  user;
    }

    @DeleteMapping("/service/deleteUser")
    public User deleteUser(@RequestParam("id") int id,
                           @RequestParam("name") String name,
                           @RequestParam("phone") String phone){
        //业务处理逻辑
        System.out.println("服务提供者deleteuser2.。。。。。。。");
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPhone(phone);
        //rest模板之所以有get、put、delete、post等，主要用在同一个接口地址区别调用不同功能上
        //http://xxx.xxx.xxx:8080/api/user/1    ---查询、删除
        //@RequestMapping(value="/api/user/{id}",method=RequestMethod.GET)  -----调用查询功能
        //@RequestMapping(value="/api/user/{id}",method=RequestMethod.DELETE)   ---调用删除功能

        return  user;
    }
}
