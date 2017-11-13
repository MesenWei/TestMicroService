package com.mesen.web.interfacecontroller;

import com.mesen.config.AllConfiguration;
import com.mesen.config.EurekaConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 因为eureka项目目前有密码，所以不能直接访问，应当去配置一下。
 */
@FeignClient(name="micro-server-eureka",url="http://localhost:8761",configuration = {EurekaConfiguration.class, AllConfiguration.class})
public interface EurekaFeignClientDefault {
    @RequestMapping(value = "/eureka/apps/{serviceName}")
    String findServiceInfoFromEurekaByServiceName(@PathVariable("serviceName") String serviceName);
}
