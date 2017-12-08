package com.mesen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * 请求地址：http://localhost:7802/turbine.stream。
 *
 * Created by maosheng on 2017/12/1
 */
@SpringBootApplication
@EnableTurbine
public class HystrixTurbineApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixTurbineApplication.class, args);
	}
}
