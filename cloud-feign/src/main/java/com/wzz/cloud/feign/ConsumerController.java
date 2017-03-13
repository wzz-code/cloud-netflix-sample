package com.wzz.cloud.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhizhao.wang on 2017/3/13.
 */
@RestController
public class ConsumerController {

    @Autowired
    ComputeClient computeClient;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return computeClient.hello();
    }

}
