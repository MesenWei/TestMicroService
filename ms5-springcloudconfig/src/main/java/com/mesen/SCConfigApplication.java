package com.mesen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by maosheng on 2017/12/11
 */

@SpringBootApplication
@EnableConfigServer
public class SCConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(SCConfigApplication.class, args);
    }
}
