package com.mesen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 之前，所有的微服务（一定也包含eureka）都是通过读取本地resources目录下的配置文件来初始化微服务的，
 * 但是这种方式有很明显的弊端：
 * 微服务肯定不是一个，一个微服务负载均衡肯定也不是一个，那么如果更改一个微服务的配置，相应的也要去更改其他负载均衡的服务，这是很麻烦的。
 * 现在spring cloud为我们提供一种解决方案-spring cloud config：
 * 所有微服务的配置文件都放在版本控制工具SVN或Github上，编写一个微服务-server专门读取版本控制工具中的配置文件，
 * 然后原本读取本地resources目录下配置文件的微服务-client，都来访问这个server端，通过server端来获取自己的配置文件。
 *
 * Created by maosheng on 2017/12/11
 */

@SpringBootApplication
@EnableConfigServer
public class SCConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SCConfigServerApplication.class, args);
    }
}
