package com.mesen;

import com.mesen.annotation.ExcludeFromComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * 异常在微服务之前存在，有两种类型，一种是Exception，一种是服务挂掉了。
 * 一般在controller层，我们使用try catch的方式来处理service层出现的异常，但是无法快速响应服务器挂掉的这种情况。
 * 这个时候就体现出熔断技术的重要性了。
 * A->B->C-D
 * 		D出现了Exception：
 * 			D出现了Exception，则D有Fallback回调机制，会调用对应的方法，将错误信息返回给C，此时C应当对D返回的信息做判断处理，
 * 			C处理完成后，将结果返回给B，B对C返回的结果进行处理，B处理完成后再返回给A。
 * 		D服务挂掉了：
 * 			D服务挂掉后，在C服务上也有实现了熔断机制，故C可以快速处理这种情况，而不是像一前一样要等很久。
 * 			C将错误信息返回给B
 * 				（此时只有D的服务器有问题，没有在规定时间内返回响应的时候才会使C的Fallback机制执行（C中出现异常也会执行Fallback机制），
 * 				所以此时C（任何服务）返回的错误信息应该是有一个默认的信息：服务器无响应，C中的异常引起Fallback机制执行的时候，我们应当给予其他信息），
 * 			B对C返回的结果尽心处理，B处理完成后在返回给A。
 *
 * 熔断有两种方式：
 * 服务端失败回调-fallback
 * 客户端失败回调-服务降级
 *
 * Created by maosheng on 2017/11/15
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker
@ComponentScan(excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = ExcludeFromComponentScan.class) })
public class HystrixUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixUserApplication.class, args);
	}
}
