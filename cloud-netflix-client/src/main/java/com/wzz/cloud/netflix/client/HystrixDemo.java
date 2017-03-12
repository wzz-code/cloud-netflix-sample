package com.wzz.cloud.netflix.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhizhao.wang on 2017/3/12.
 */
@RestController
public class HystrixDemo {

    @Autowired
    private HelloService service;

    @RequestMapping("/hystrix-hello")
    public String hello() {
        return this.service.hello();
    }

    @Component
    public static class HelloService {

        @HystrixCommand
        public String hello() {
            return "Hello World";
        }
        public String fallback() {
            return "Fallback";
        }
    }

}
