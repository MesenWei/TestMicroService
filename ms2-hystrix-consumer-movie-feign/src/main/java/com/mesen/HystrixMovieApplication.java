package com.mesen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Feign默认整合了Ribbon，所以不用再配置Ribbon了。
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
public class HystrixMovieApplication {
	public static void main(String[] args) {
		SpringApplication.run(HystrixMovieApplication.class, args);
	}

}
