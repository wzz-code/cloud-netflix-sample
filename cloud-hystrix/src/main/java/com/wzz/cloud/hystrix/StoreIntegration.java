package com.wzz.cloud.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by zhizhao.wang on 2017/3/8.
 */
@Component
public class StoreIntegration {

    @HystrixCommand(fallbackMethod = "defaultStores")
    public Object getStores(Map<String, Object> parameters) {
        System.out.println("getStores");
        return null;
    }

    public Object defaultStores(Map<String, Object> parameters) {
        System.out.println("defaultStores");
        return null; /* something useful */
    }
}
