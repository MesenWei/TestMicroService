package com.mesen.controller;

import com.mesen.api.user.interfacecontroller.HystrixUserFeignClient;
import com.mesen.api.user.interfacecontroller.HystrixUserFeignClient2;
import com.mesen.api.user.interfacecontroller.HystrixUserFeignClient3;
import com.mesen.commons.timeoutfallback.TimeoutFallback;
import com.mesen.vo.PageVo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * 熔断机制，为了解决雪崩效应。在spring cloud 中，熔断机制是通过Hystrix实现的。
 *
 * 熔断技术
 * 服务降级
 */
@RestController
@RequestMapping({"/movie"})
public class HystrixMovieController extends TimeoutFallback{
    @Autowired
    private HystrixUserFeignClient hystrixUserFeignClient;
    @Autowired
    private HystrixUserFeignClient2 hystrixUserFeignClient2;
    @Autowired
    private HystrixUserFeignClient3 hystrixUserFeignClient3;

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

    /**
     * 测试服务器宕机。
     *
     * @return
     */
    @GetMapping("testfactorydown")
    @HystrixCommand(fallbackMethod = "timeoutFallback")
    public PageVo testFactoryDown(){
        PageVo userListGood = null;
        try{
            userListGood = hystrixUserFeignClient3.getUserListBad2(20L);
        }catch (Exception e){
            userListGood = autoWired(Optional.of(e));
        }
        return userListGood;
    }

    /**
     * 测试服务器方法运算超时。
     *
     * @return
     */
    @GetMapping("testfactorytimeout")
    @HystrixCommand(defaultFallback = "timeoutFallback")
    public PageVo testFactoryTimeout(String id){
        PageVo userListGood = null;
        try{
            userListGood = hystrixUserFeignClient3.getUserListBad3(20L);
        }catch (Exception e){

        }
        return userListGood;
    }

    public PageVo fallback(Long id){
        return new PageVo("1","降级");
    }
}
