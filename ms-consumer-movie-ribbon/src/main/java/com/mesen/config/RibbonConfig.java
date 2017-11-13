package com.mesen.config;

import com.mesen.annotation.ExcludeFromComponentScan;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Ribbon Client的java代码配置方式。
 *
 * Ribbon Client的配置有三种方式，优先级分别为：配置文件>java类>默认。
 *
 * 一个服务消费者对应多个服务提供者，Ribbon对多个服务提供者可以进行负载均衡，Ribbon可单独对某些服务提供者进行负载均衡配置。
 * 每一个RibbonClient注解代表RestTemplate对指定的（例：micro-server-user）服务提供者进行了负载均衡。如果想对其他微服务进行配置，则再加一个注解即可。
 * RibbonClient注解可以写到Ribbon的config类上，也可以写到SpringBoot的启动类上。
 * Ribbon的配置类，不建议被ComponentScan注解扫描到，如果被扫描到，Ribbon的配置会对所有服务提供者（micro-server-user，micro-server-user2......）起作用。
 * 如果config的类被ComponentScan扫描到了，我们可以使用excludeFilters来排除ComponentScan的扫描。
 *
 * Created by mesen on 2017.11.12
 */

/**
 * TODO 对于Configuration注解的疑惑：
 * Configuration注解修饰的类，不用被spring扫描到，也能被当做spring的容器，
 * 但是在不被扫描到的情况下，被Bean注解修饰的方法，无法被自动注入。
 * 如果Configuration注解修饰的类，被spring扫描到了，那时被Bean注解修饰的方法，才会被自动注入。
 *
 * 这是为什么？
 * 如果Configuration注解修饰的类没有被spring扫描到的话，这个类就不会被加扫描，也就不会被spring认识，
 * 里面配置的所有东西都无效才对，但结果是即使没有被扫到，里面的配置仍是有效的，但是Bean修饰的方法的实例却无法被注入！
 */
@Configuration
@RibbonClient(name = "micro-server-user",configuration = RibbonConfig.class)//写在Application上也可以（不建议）。
@ExcludeFromComponentScan
public class RibbonConfig {
    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }
}
