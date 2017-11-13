package com.mesen.web.controller;

import com.mesen.api.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping({"/movie"})
public class MovieController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("getuser")
    public List<User> getUser(){
        return restTemplate.getForObject("http://admin:admin123@localhost:7900/user/getuser",List.class);
    }
}
