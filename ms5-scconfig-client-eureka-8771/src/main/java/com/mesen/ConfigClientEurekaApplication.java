package com.mesen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Create by maosheng on 2017/12/17
 */
@EnableEurekaServer
@SpringBootApplication
public class ConfigClientEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientEurekaApplication.class, args);
	}
}
