package com.mesen.api.user.interfacecontroller;

import com.mesen.api.user.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * FeignClient注解代表，当前接口为访问服务提供者的接口，name代访问哪个服务。
 * 注意：
 * 1.虽然GetMapping注解等同于RequestMapping + method，但是在Feign中，GetMapping不好用。
 * 2.feign认为，方法的参数即请求的GET参数和POST参数，在参数中的注解的value都必须有值，如RequestParam、PathVariable，否则启动报错。
 * 3.feign认为，参数不加任何注解时，请求为GET，即使【method = RequestMethod.POST】。
 *
 */
@FeignClient(name = "micro-server-user")
@RequestMapping({"/user"})
public interface UserFeignClient {
    @RequestMapping(value = "getuser",method = RequestMethod.GET)
    List<User> getUser();

    /**
     * 目标方法getuserfeign为GET请求，则需要加在参数上加RequestParam注解（必须加）。
     * @param id
     * @return
     */
    @RequestMapping(value = "getuserfeignget",method = RequestMethod.GET)
    List<User> getUserFeignGet(@RequestParam("id") Long id);

    @RequestMapping(value = "getuserfeignget2/{id}",method = RequestMethod.GET)
    List<User> getUserFeignGet2(@PathVariable("id") Long id);

    @RequestMapping(value = "getuserfeignget3",method = RequestMethod.GET)
    User getUserFeignGet3(@RequestParam("user") User user);

    /**
     * 目标方法getuserfeignpost为POST请求，则需要在参数上加RequestBody（可以不加，最好是加）。
     * @param id
     * @return
     */
    @RequestMapping(value = "getuserfeignpost",method = RequestMethod.POST)
    Long getUserFeignPost(@RequestBody Long id);

    @RequestMapping(value = "getuserfeignpost2",method = RequestMethod.POST)
    User getUserFeignPost2(@RequestBody User user);

}
