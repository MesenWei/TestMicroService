package com.mesen.controller;

import com.mesen.api.user.interfacecontroller.HystrixUserFeignClient;
import com.mesen.api.user.interfacecontroller.HystrixUserFeignClient2;
import com.mesen.vo.PageVo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 熔断机制，为了解决雪崩效应。在spring cloud 中，熔断机制是通过Hystrix实现的。
 *
 * 熔断技术
 * 服务降级
 */
@RestController
@RequestMapping({"/movie"})
public class HystrixMovieController {
    @Autowired
    private HystrixUserFeignClient hystrixUserFeignClient;
    @Autowired
    private HystrixUserFeignClient2 hystrixUserFeignClient2;

    @GetMapping("getuserlistbad")
    public PageVo getUserListBad(Long id){
        PageVo userListGood = hystrixUserFeignClient.getUserListBad(id);
        return userListGood;
    }

    @GetMapping("getuserlistbad2")
    public PageVo getUserListBad2(Long id){
        PageVo userListGood = hystrixUserFeignClient2.getUserListBad2(id);
        return userListGood;
    }

    @GetMapping("getuserlistgood")
    public PageVo getUserListGood(Long id){
        PageVo userListGood = hystrixUserFeignClient.getUserListGood(id);
        return userListGood;
    }

    @GetMapping("getuserlistgood2")
    @HystrixCommand(fallbackMethod = "fallback")
    public PageVo getUserListGood2(Long id){
        PageVo userListGood = hystrixUserFeignClient.getUserListGood(id);
        return userListGood;
    }

    public PageVo fallback(Long id){
        return new PageVo("1","降级");
    }
}
