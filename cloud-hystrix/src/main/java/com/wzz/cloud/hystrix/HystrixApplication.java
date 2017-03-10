package com.wzz.cloud.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by zhizhao.wang on 2017/3/8.
 */
@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
@RestController
public class HystrixApplication extends SpringBootServletInitializer {

    @Autowired
    private StoreIntegration integraion;


    public static void main(String[] args) {
        new SpringApplicationBuilder(HystrixApplication.class).web(true).run(args);
    }

    @RequestMapping("/test")
    public String home() {
        this.integraion.getStores(null);
        return "Hello World";
    }
}
