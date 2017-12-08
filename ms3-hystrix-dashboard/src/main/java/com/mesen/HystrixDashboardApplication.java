package com.mesen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * Hystrix仪表盘，监控服务的请求信息，请求地址：http://localhost:7801/hystrix。
 * 本质是监控有HystrixCommand注解的controller层的方法调用情况。
 * 		1.controller层的方法必须加HystrixCommand注解。
 *
 * Created by maosheng on 2017/12/1
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixDashboardApplication.class, args);
	}
}
