package com.mesen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka的高可用-heigh availability。
 *
 * Created by mesen on 2017/10/22.
 */

@EnableEurekaServer
@SpringBootApplication
public class EurekaHaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaHaApplication.class, args);
	}
}
