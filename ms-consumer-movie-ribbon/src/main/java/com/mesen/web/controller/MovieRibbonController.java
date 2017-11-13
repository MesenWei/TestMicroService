package com.mesen.web.controller;

import com.mesen.api.user.entity.User;
import com.mesen.config.RibbonConfig;
import com.netflix.loadbalancer.IRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 *
 *  Created by mesen on 2017.11.11
 */
@RestController
@RequestMapping({"/movie"})
public class MovieRibbonController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;


    @GetMapping("getuser-bad1")
    public List<User> getUserBad1(){
        /**
         * RestTemplate在增加了LoadBalanced注解后，所有IP都可以用微服务的spring.application.name来代替。
         * VIP（Virtual IP）：micro-server-user，用户微服务的spring.application.name。
         */
        System.out.println("log--------movie getuser-bad1");
        return restTemplate.getForObject("http://MICRO-SERVER-USER:7901/user/getuser",List.class);
    }

    /**
     * 测试Ribbon的配置-负载均衡策略会对哪些服务提供者起作用。
     * 测试结果：
     * 如果Ribbon的配置所在的ComponentScan注解扫描到，那么Ribbon的配置对所有的服务起作用；
     * 反之，Ribbon的配置只会对配置了的服务都起作用。
     * @return
     */
    @GetMapping("/testribbonruleforserver")
    public String test() {
        System.out.println("===============================");
        ServiceInstance serviceInstance = loadBalancerClient.choose("micro-server-user");
        System.out.println("111" + ":" + serviceInstance.getServiceId() + ":" + serviceInstance.getHost() + ":" + serviceInstance.getPort());

        ServiceInstance serviceInstance2 = loadBalancerClient.choose("micro-server-user2");
        System.out.println("222" + ":" + serviceInstance2.getServiceId() + ":" + serviceInstance2.getHost() + ":" + serviceInstance2.getPort());

        return "1";
    }
}
