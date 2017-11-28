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
 * 			D出现了Exception，则D有Fallback回调机制，会调用对应的方法，将错误信息返回给C，因为我们用了Fallback机制，所以C的Hystrix自动识别了D的错误信息，
 * 			C自动将错误信息返回给B，因为B也使用了Fallback机制，所以B的Hystrix自动处理C的错误信息，B自动将错误信息再返回给A。
 * 		D服务挂掉了：
 * 			D服务挂掉后（D端的Fallback回调机制肯定无效了），因在C服务上也实现了熔断机制，故C可以快速处理这种情况，而不是像一前一样要等很久。
 * 			C将错误信息返回给B
 * 				（此时只有D的服务器有问题，没有在规定时间内返回响应的时候才会使C的Fallback机制执行（C中出现异常也会执行Fallback机制），
 * 				所以此时C（任何服务）返回的错误信息应该是有一个默认的信息：服务器无响应，C中的异常引起Fallback机制执行的时候，我们应当给予其他信息），
 * 			B对C返回的结果尽心处理，B处理完成后在返回给A。
 *
 * 熔断有两种机制：
 * 		1.服务端失败回调-Hystrix提供的Fallckback
 * 			服务端失败回调的是服务端的Fallback。
 * 			使用Fallback两个优点：
 * 				a.可以处理线程执行超时情况；
 * 				b.A->B->C-D，D出现异常使用了Fallback机制时，C->B->A的异常传递不用我们去处理，Hystrix帮我们出来了。
 * 				  （如果不用Fallback机制，我们需要每一个异常都要判断异常信息来做出相应的动作）。
 * 				  但我们仍要自行捕获异常（捕获了需要再抛出，实质是抛给了Hystrix的Fallback方法），因为这样异常信息对我们来讲才是可控的。
 *
 * 		2.服务端宕机回调-服务降级
 * 			服务端宕机后，是客户端的Feign提供的熔断机制来处理的，回调的是Feign的FallbackFactory。
 *
 * 异常编码定义：
 * 	0：ok（VO默认）；-1：宕机（Fallback默认）；-2：超时；大于0，其他异常；-3：controller未捕获异常，未知异常。
 *  异常message定义：服务ID#异常code#异常描述
 *
 * 实现熔断机制，有三种方式：
 * 1.在方法上使用注解HystrixCommand： @HystrixCommand(fallbackMethod = "fallbackForService")
 * 		a.这种使用方式类似于try catch语句。
 * 		b.当HystrixCommand注解修饰的方法，长时间未响应时（时间多少是在yml文件中配置的），也会调用fallbackMethod。
 * 2.与Feign整合，在Feign Client的类上添加注解：@FeignClient(name = "micro-server2-hystrix-user",fallback = HystrixUserFallback.class)
 * 		这种配置方式的缺点是，无法动态返回异常信息。
 * 3.与Feign整合，使用FallbackFactory。FallbackFactory也是Feign提供的熔断机制。可以自动处理Feign Client请求到的异常，
 * 	 主要包括服务端抛出的异常，和服务端宕机。
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
