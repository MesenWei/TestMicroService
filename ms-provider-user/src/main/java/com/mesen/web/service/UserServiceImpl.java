package com.mesen.web.service;

import org.springframework.stereotype.Service;

/**
 * Created by maosheng on 2017/11/12
 */
@Service
public class UserServiceImpl implements UserService{
    @Override
    public String getSth() {
        return "jintian";
    }
}
