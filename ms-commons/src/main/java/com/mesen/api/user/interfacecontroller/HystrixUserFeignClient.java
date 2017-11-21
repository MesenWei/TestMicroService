package com.mesen.api.user.interfacecontroller;

import com.mesen.vo.PageVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 测试类中增加熔断回调方法。
 *
 * Create by maosheng on 2017-11-16.
 */
@FeignClient(name = "micro-server2-hystrix-user")
public interface HystrixUserFeignClient {
    @RequestMapping(value = "/user/getuserlistbad",method = RequestMethod.GET)
    PageVo getUserListBad(@RequestParam("id") Long id);

    @RequestMapping(value = "/user/getuserlistgood",method = RequestMethod.GET)
    PageVo getUserListGood(@RequestParam("id") Long id);
}
