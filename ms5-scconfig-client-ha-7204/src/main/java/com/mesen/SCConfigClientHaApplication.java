package com.mesen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *
 * Created by maosheng on 2018/1/5.
 */
@EnableEurekaClient
@SpringBootApplication
public class SCConfigClientHaApplication {
    public static void main(String[] args) {
        SpringApplication.run(SCConfigClientHaApplication.class, args);
    }
}
