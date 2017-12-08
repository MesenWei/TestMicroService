package com.mesen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 通过spring cloud官方文档中的说明，在使用zuul代理上传请求时，不能直接上传太大的文件，
 * 我们应当使用微服务来实现真正的上传操作，而不是使用zuul来实现，实现方式只需要在请求路径上加上
 * /zuul/**即可。
 * 小文件请求路径：
 * http://localhost:8801/ul/upload
 * 大文件请求路径：
 * http://localhost:8801/zuul/ul/upload
 *
 * Created by maosheng on 2017/12/3
 */
@EnableZuulProxy
@SpringBootApplication
public class GatewayZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayZuulApplication.class, args);
	}
}
