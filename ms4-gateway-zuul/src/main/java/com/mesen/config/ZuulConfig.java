package com.mesen.config;

import com.mesen.zuulfilter.AuthorizedRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulConfig {
	@Bean
	public AuthorizedRequestFilter getAuthorizedRequestFilter() {
		return new AuthorizedRequestFilter() ;
	}
}
