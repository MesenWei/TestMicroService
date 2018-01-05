package com.mesen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 *
 * Create by maosheng on 2018/1/4.
 */
@SpringBootApplication
public class ConfigClientManualRefreshApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientManualRefreshApplication.class, args);
	}
}
