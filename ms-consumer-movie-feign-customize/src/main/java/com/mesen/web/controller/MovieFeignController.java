package com.mesen.web.controller;

import com.mesen.web.interfacecontroller.EurekaFeignClientDefault;
import com.mesen.web.interfacecontroller.UserFeignClientCustomize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/movie"})
public class MovieFeignController {
    @Autowired
    private UserFeignClientCustomize userFeignClientCustomize;
    @Autowired
    private EurekaFeignClientDefault eurekaFeignClientDefault;

    @GetMapping("getusergood")
    public List getUser(){
        return userFeignClientCustomize.getUser(2L);
    }

    @GetMapping("getusergood2")
    public String getUser2(String serviceName){
        return eurekaFeignClientDefault.findServiceInfoFromEurekaByServiceName(serviceName);
    }
}
