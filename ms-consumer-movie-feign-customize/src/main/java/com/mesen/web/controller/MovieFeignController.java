package com.mesen.web.controller;

import com.mesen.web.interfacecontroller.EurekaFeignClientDefault;
import com.mesen.web.interfacecontroller.UserFeignClientCustomize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
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
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("getusergood")
    public List getUser(){
        return userFeignClientCustomize.getUser(2L);
    }

    @GetMapping("getusergood2")
    public String getUser2(String serviceName){
        return eurekaFeignClientDefault.findServiceInfoFromEurekaByServiceName(serviceName);
    }

    @GetMapping("testfeignribbon")
    public void testFeignRibbon(){
        System.out.println("===============================");
        ServiceInstance serviceInstance = loadBalancerClient.choose("micro-server-user");
        System.out.println("111" + ":" + serviceInstance.getServiceId() + ":" + serviceInstance.getHost() + ":" + serviceInstance.getPort());

        ServiceInstance serviceInstance2 = loadBalancerClient.choose("micro-server-user2");
        System.out.println("222" + ":" + serviceInstance2.getServiceId() + ":" + serviceInstance2.getHost() + ":" + serviceInstance2.getPort());
    }
}
