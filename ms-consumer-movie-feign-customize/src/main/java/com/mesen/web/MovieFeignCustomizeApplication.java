package com.mesen.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * 通过声明式来配置FeignClient
 * create by maosheng at 2017-10-19 23:35:10
 */
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class MovieFeignCustomizeApplication {
	public static void main(String[] args) {
		SpringApplication.run(MovieFeignCustomizeApplication.class, args);
	}

}
