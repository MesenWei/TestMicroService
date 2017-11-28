package com.mesen.controller;

import com.mesen.api.user.entity.User;
import com.mesen.commons.exception.MyRuntimeException;
import com.mesen.commons.fallbackfactory.ControllerCommonFallback;
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
public class UserTestController extends ControllerCommonFallback {
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
    @HystrixCommand(defaultFallback = "controllerFbMethod")
    public PageVo getUserListBad2(Long id){
        PageVo userListGood = getUserListGood(id);
        try{
            if(true)
                throw new MyRuntimeException("USER#1#我疯狂的出了个错！");
        } catch (MyRuntimeException e){
            throw new MyRuntimeException("USER#1#我疯狂疯狂疯狂疯狂的出了个错！");
        }

        return userListGood;
    }

    /**
     * 验证访问超时情况。
     *
     * 测试发现
     *      1.在方法上加了HystrixCommand注解，虽然try catch 能捕获异常，使Fallback不生效，但是在方法运算超时的情况下，
     *      Fallback仍然有效，不知道这是bug，还是特有功能。
     *      2.HystrixCommand中的属性fallbackMethod是Fallback的回调函数，要求此函数的参数必须与HystrixCommand修饰的方法的参数一致，
     *      但，如果将fallbackMethod换成defaultFallback，此时，无论HystrixCommand修饰的方法的参数是什么，
     *      Fallback回调函数的参数为空即可正常回调，不知道这是bug，还是特有功能。
     *
     * @param id
     * @return
     */
    @GetMapping("getuserlistbad3")
    @HystrixCommand(defaultFallback = "controllerFbMethod")
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
