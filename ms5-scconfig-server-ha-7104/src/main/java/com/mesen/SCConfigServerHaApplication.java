package com.mesen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 *
 * Create by maosheng on 2018/1/5.
 */
@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
public class SCConfigServerHaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SCConfigServerHaApplication.class, args);
	}
}
