package com.wzz.cloud.netflix.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by zhizhao.wang on 2017/3/6.
 */
@SpringBootApplication
@EnableEurekaServer
public class NetflixServerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(NetflixServerApplication.class).web(true).run(args);
    }
}
