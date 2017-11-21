package com.mesen.api.user.interfacecontroller;

import com.mesen.vo.PageVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 测试类中增加熔断回调方法。
 * FeignClient能与Fallback配合使用，这一点，我个人认为是非常好的。
 *
 * Create by maosheng on 2017-11-16.
 */
@FeignClient(name = "micro-server2-hystrix-user",fallback = HystrixUserFallback.class)
public interface HystrixUserFeignClient2 {
    @RequestMapping(value = "/user/getuserlistbad2",method = RequestMethod.GET)
    PageVo getUserListBad2(@RequestParam("id") Long id);
}
