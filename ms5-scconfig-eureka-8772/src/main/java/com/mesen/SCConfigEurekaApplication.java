package com.mesen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * spring cloud config server注册到eureka中，实现其高可用。
 *
 * Create by maosheng on 2018/1/5.
 */
@EnableEurekaServer
@SpringBootApplication
public class SCConfigEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SCConfigEurekaApplication.class, args);
	}
}
