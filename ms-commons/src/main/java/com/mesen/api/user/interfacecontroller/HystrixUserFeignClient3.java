package com.mesen.api.user.interfacecontroller;

import com.mesen.api.user.fallbackgood.HystrixFallbackFactory;
import com.mesen.vo.PageVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 测试FallbackFactory
 * Create by maosheng on 2017-11-16.
 */
@FeignClient(name = "micro-server2-hystrix-user",fallbackFactory = HystrixFallbackFactory.class)
public interface HystrixUserFeignClient3 {
    @RequestMapping(value = "/user/getuserlistbad2",method = RequestMethod.GET)
    PageVo getUserListBad2(@RequestParam("id") Long id);

    @RequestMapping(value = "/user/getuserlistbad3",method = RequestMethod.GET)
    PageVo getUserListBad3(@RequestParam("id") Long id);
}
