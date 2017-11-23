package com.mesen.controller;

import com.mesen.api.user.entity.User;
import com.mesen.commons.timeoutfallback.TimeoutFallback;
import com.mesen.vo.PageVo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * 测试服务端失败：Fallback。
 *
 * Created by maosheng on 2017/11/15
 */
@RestController
@RequestMapping("/user")
public class UserTestController extends TimeoutFallback{

    /**
     * 在类中增加熔断是的回调方法。
     * 如果添加try.catch语句，则Fallback回调方法无效。
     *
     * @return
     */
    @GetMapping("getuserlistbad")
    @HystrixCommand(fallbackMethod = "fallbackForService")
    public PageVo getUserListBad(Long id){
        PageVo userListGood = getUserListGood(id);

        if(true)
            throw new RuntimeException("");

        return userListGood;
    }

    /**
     * 在FeignClient中增加了熔断回调方法。
     * 验证客户端降级。
     *
     * @return
     */
    @GetMapping("getuserlistbad2")
    public PageVo getUserListBad2(Long id){
        PageVo userListGood = getUserListGood(id);

        if(true)
            throw new RuntimeException("");

        return userListGood;
    }

    /**
     * 验证访问超时情况。
     *
     * @param id
     * @return
     */
    @GetMapping("getuserlistbad3")
    @HystrixCommand(fallbackMethod = "timeoutFallback")
    public PageVo getUserListBad3(Long id){
        PageVo userListGood = getUserListGood(id);

        try {
            Thread.sleep(6000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return userListGood;
    }

    @GetMapping("getuserlistgood")
    public PageVo<List<User>> getUserListGood(Long id){
        User user = new User(id,"a");
        User user2 = new User(id+1,"b");
        User user3 = new User(id+2,"c");
        List<User> list = Arrays.asList(user,user2,user3);

        return new PageVo(list);
    }


}
