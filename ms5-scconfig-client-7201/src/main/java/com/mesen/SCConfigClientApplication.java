package com.mesen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 该微服务是用来测试作为spring cloud config client端通过
 * springspring cloud config server读取Github中的配置文件用的。
 *
 * Created by maosheng on 2017/12/11
 */

@SpringBootApplication
public class SCConfigClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(SCConfigClientApplication.class, args);
    }
}
