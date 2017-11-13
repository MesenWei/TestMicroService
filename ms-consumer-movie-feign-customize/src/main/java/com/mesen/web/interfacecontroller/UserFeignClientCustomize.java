package com.mesen.web.interfacecontroller;

import com.mesen.config.AllConfiguration;
import com.mesen.config.UserConfiguration;
import com.mesen.api.user.entity.User;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

import java.util.List;

/**
 * 使用自定义的Feign Client配置。
 */
@FeignClient(name="micro-server-user",path="/user",configuration = {UserConfiguration.class, AllConfiguration.class})
public interface UserFeignClientCustomize {
    @RequestLine("GET /getuserfeignget2/{id}")
    List<User> getUser(@Param("id") Long id);
}
