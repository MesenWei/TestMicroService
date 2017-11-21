package com.mesen;

import com.mesen.annotation.ExcludeFromComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * 错误在微服务之间的存在有两种类型，一种是Exception（访问超时也认为是Exception），一种是服务宕机了。
 * A->B->C-D
 * 		D出现了Exception：
 * 			D出现了Exception，则D有Fallback回调机制，会调用对应的方法，将错误信息返回给C，此时C应当对D返回的信息做判断处理，
 * 			C处理完成后，将结果返回给B，B对C返回的结果进行处理，B处理完成后再返回给A。
 * 		D服务挂掉了：
 * 			D服务挂掉后（D端的Fallback回调机制肯定无效了），因在C服务上也实现了熔断机制，故C可以快速处理这种情况，而不是像一前一样要等很久。
 * 			C将错误信息返回给B
 * 				（此时只有D的服务器有问题，没有在规定时间内返回响应的时候才会使C的Fallback机制执行（C中出现异常也会执行Fallback机制），
 * 				所以此时C（任何服务）返回的错误信息应该是有一个默认的信息：服务器无响应，C中的异常引起Fallback机制执行的时候，我们应当给予其他信息），
 * 			B对C返回的结果尽心处理，B处理完成后在返回给A。
 *
 * 熔断有两种机制：
 * 		1.服务端失败回调-UserFallckback
 * 			服务端失败回调的是服务端的Fallback。
 * 			服务端失败回调的机制中，对于Exception，我们不使用Fallback，所以在controller中，我们依然使用try catch的方式来捕获业务层的异常；
 * 			但是，对于服务端被访问超时（即处理客户端请求时间过长）时，我们使用Fallback机制。
 *
 * 		2.服务端宕机回调-服务降级
 * 			服务端宕机后，是客户端的Hystrix来处理的，回调的是客户端的Fallback。
 * ==============
 * 		服务端返回给客户端的错误信息（异常，超时，宕机），客户端要通过try catch来处理。
 *
 * 	0：ok（VO默认）；-1：宕机（Fallback默认）；-2：超时；大于0，其他异常。
 *
 * 实现熔断机制，有三种方式：
 * 1.在方法上使用注解HystrixCommand： @HystrixCommand(fallbackMethod = "fallbackForService")
 * 		a.这种使用方式类似于try catch语句。
 * 		b.当HystrixCommand注解修饰的方法，长时间未响应时（时间多少是在yml文件中配置的），也会调用fallbackMethod。
 * 2.与Feign整合，在Feign Client的类上添加注解：@FeignClient(name = "micro-server2-hystrix-user",fallback = HystrixUserFallback.class)
 * 		这种配置方式的缺点是，无法动态返回异常信息。
 * 3.与Feign整合，使用FallbackFactory，TODO
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
