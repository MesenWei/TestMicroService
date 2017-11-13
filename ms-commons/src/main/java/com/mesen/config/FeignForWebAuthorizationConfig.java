package com.mesen.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 在使用Feign进行发送请求时，通过BasicAuthRequestInterceptor可以设置请求时的用户名和密码。
 *
 * Created by maosheng on 2017/11/12
 */
@Configuration
public class FeignForWebAuthorizationConfig {
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("admin", "admin123");
    }
}
