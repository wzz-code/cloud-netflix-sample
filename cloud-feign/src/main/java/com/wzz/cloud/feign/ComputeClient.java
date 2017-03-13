package com.wzz.cloud.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zhizhao.wang on 2017/3/13.
 */
@FeignClient("netflix-client")
public interface ComputeClient {

    @RequestMapping(method = RequestMethod.GET, value = "/hystrix-hello")
    String hello();
}
