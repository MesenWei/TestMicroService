package com.mesen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 通过@EnableEurekaServer注解启动一个服务注册中心提供给其他应用进行对话。
 *
 * 2017-10-6
 */

/*
 EnableEurekaServer注解代表当前项目是一个Eureka项目，该项目对我们来说是一个服务发现的服务端。

 是Eureka服务（server）端或Eureka客户（client）端，是对于我们来说的，
 对于Eureka本身来说，Eureka自己才是服务端，其余的Eureka项目都是客户端，
 只不过Eureka把其中某一个客户端为我们提供服务端的功能。
 */
@EnableEurekaServer

@SpringBootApplication
public class EurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaApplication.class, args);
	}
}
