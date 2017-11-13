package com.mesen;

import com.mesen.annotation.ExcludeFromComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/*
 1.Application作为SpringBoot的启动类，必须放在一个包下（没有包package，启动会报错）。
 2.SpringBootApplication注解默认实现了ComponentScan的功能，默认扫描的包是：
 增加了SpringBootApplication注解的类所在的包，及子包。
 */

/*
 EnableDiscoveryClient注解代表当前项目是一个Eureka项目，该项目对我们来说是一个服务发现的客户端。

 EnableDiscoveryClient和EnableEurekaClient注解都能起到使项目成为Eureka项目的客户端的作用，两者的区别是：
 EnableDiscoveryClient是一个服务发现客户端注解，可为多个服务发现组件（zk、etcd、zureka）提供支持；
 EnableEurekaClient只是Eureka的一个注解，其属于EnableDiscoveryClient。
 */

/*
ComponentScan默认扫描当前类所在的包以及子包，添加了扫描包后，默认的扫描规则无效。
*/

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = ExcludeFromComponentScan.class) })
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}
}
