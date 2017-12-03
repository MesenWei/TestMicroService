package com.mesen.controller;

import com.mesen.api.user.entity.User;
import com.mesen.api.user.interfacecontroller.HystrixUserFeignClient;
import com.mesen.api.user.interfacecontroller.HystrixUserFeignClient2;
import com.mesen.api.user.interfacecontroller.HystrixUserFeignClient3;
import com.mesen.commons.fallbackfactory.ControllerCommonFallback;
import com.mesen.vo.PageVo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 熔断机制，为了解决雪崩效应。在spring cloud 中，熔断机制是通过Hystrix实现的。
 *
 * 熔断技术
 * 服务降级
 */
@RestController
@RequestMapping({"/movie"})
public class HystrixMovieController extends ControllerCommonFallback {
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
    @HystrixCommand(defaultFallback = "controllerFbMethod")
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
    @HystrixCommand(defaultFallback = "controllerFbMethod")
    public PageVo testFactoryTimeout(String id){
        PageVo userListGood = null;
        try{
            userListGood = hystrixUserFeignClient3.getUserListBad3(20L);
        }catch (Exception e){

        }
        return userListGood;
    }

    @GetMapping("getlocal")
    @HystrixCommand(defaultFallback = "controllerFbMethod")
    public PageVo getLocal(Long id){
        User user = new User(id,"a");
        User user2 = new User(id+1,"b");
        User user3 = new User(id+2,"c");
        User user4 = new User(id+3,"c");
        List<User> list = Arrays.asList(user,user2,user3,user4);

        return new PageVo(list);
    }

    public PageVo fallback(Long id){
        return new PageVo("1","降级");
    }
}
