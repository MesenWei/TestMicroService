package com.mesen.web.controller;

import com.mesen.api.user.entity.User;
import com.mesen.web.service.UserService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping({"/user"})
public class UserController {
    @Autowired
    private EurekaClient eurekaClient;
    @Autowired
    private UserService userService;

    @GetMapping("get")
    public String test(){
        return userService.getSth();
    }

    @GetMapping("getsessionid")
    public String getSessionId(HttpServletRequest request){
        return request.getSession().getId();
    }

    @GetMapping("getuser")
    public List<User> getUser(){
        User user = new User(1L,"a");
        User user2 = new User(2L,"b");
        User user3 = new User(3L,"c");
        List<User> list = Arrays.asList(user,user2,user3);

        System.out.println("log--------user1 getuser");

        return list;
    }

    @GetMapping("getuserfeignget")
    public List<User> getUserFeignGet(Long id){
        User user = new User(id,"a");
        User user2 = new User(id+1,"b");
        User user3 = new User(id+2,"c");
        List<User> list = Arrays.asList(user,user2,user3);

        System.out.println("log--------user1 getuser");

        return list;
    }

    @GetMapping("getuserfeignget2/{id}")
    public List<User> getUserFeignGet2(@PathVariable Long id){
        return getUserFeignGet(id);
    }

    @GetMapping("getuserfeignget3")
    public User getUserFeignGet3(User user){
        return user;
    }

    @PostMapping("getuserfeignpost")
    public Long getUserFeignPost(@RequestBody Long id){
        return id;
    }

    @PostMapping("getuserfeignpost2")
    public User getUserFeignPost2(@RequestBody User user){
        return user;
    }

    @GetMapping("/getserviceurl")
    public String serviceUrl() {
        /**
         * @param:virtualHostname spring.application.name
         */
        InstanceInfo instance = eurekaClient.getNextServerFromEureka("micro-server-user", false);
        return instance.getHomePageUrl();
    }
}
