package com.mesen.web.controller;

import com.mesen.api.user.entity.User;
import com.mesen.api.user.interfacecontroller.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/movie"})
public class MovieFeignController {
    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("getusergood")
    public List getUser(){
        return userFeignClient.getUser();
    }

    @GetMapping("getusergood2")
    public List getUser2(){
        return userFeignClient.getUserFeignGet(1L);
    }

    @GetMapping("getusergood3")
    public List getUser3(){
        return userFeignClient.getUserFeignGet2(1L);
    }

    @GetMapping("getusergood4")
    public Long getUser4(){
        return userFeignClient.getUserFeignPost(1L);
    }

    @GetMapping("getusergood5")
    public User getUser5(){
        User user = new User(1234L,"maosheng");
        return userFeignClient.getUserFeignPost2(user);
    }

}
