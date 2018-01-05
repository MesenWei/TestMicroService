package controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Create by maosheng on 2018/1/4.
 */
@RestController
public class ConfigClientRest {
	/**
	 * 应用的服务名称
	 */
	@Value("${spring.application.name}")
	private String applicationName;

	@RequestMapping("/config")
	public String getConfig() {
		return "ApplicationName = " + this.applicationName;
	}
}
