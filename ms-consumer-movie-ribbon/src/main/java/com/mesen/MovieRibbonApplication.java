package com.mesen;

import com.mesen.annotation.ExcludeFromComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

/**
 * Ribbon Client的java代码配置方式。
 *
 * Ribbon Client的配置有三种方式，优先级分别为：配置文件>java类>默认。

 * 一个服务消费者对应多个服务提供者，Ribbon对多个服务提供者可以进行负载均衡，Ribbon可单独对某些服务提供者进行负载均衡配置。
 * 每一个RibbonClient注解代表RestTemplate对指定的（例：micro-server-user）服务提供者进行了负载均衡。如果想对其他微服务进行配置，则再加一个注解即可。
 * RibbonClient注解可以写到Ribbon的config类上，也可以写到SpringBoot的启动类上。
 * Ribbon的配置类，不建议被ComponentScan注解扫描到，如果被扫描到，Ribbon的配置会对所有服务提供者（micro-server-user，micro-server-user2......）起作用。
 * 如果config的类被ComponentScan扫描到了，我们可以使用excludeFilters来排除ComponentScan的扫描。
 *
 *  Created by mesen on 2017.11.11
 */
@EnableEurekaClient
@SpringBootApplication
//@RibbonClient(name = "micro-server-user",configuration = RibbonConfig.class)//建议写在各自的Config上。
@ComponentScan(excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = ExcludeFromComponentScan.class) })
public class MovieRibbonApplication {
	public static void main(String[] args) {
		SpringApplication.run(MovieRibbonApplication.class, args);
	}

	/**
	 * LoadBalanced注解，使得RestTemplate具备了Ribbon提供的负载均衡能力。
	 * Ribbon默认提供的负载均衡的策略是轮询策略。
	 * @return
	 */
	@Bean
    @LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
