package com.mesen.api.user.interfacecontroller;

import com.mesen.vo.PageVo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * Created by maosheng on 2017/11/16
 */
@Component
public class HystrixUserFallback implements HystrixUserFeignClient2 {
    public PageVo getUserListBad2(@RequestParam("id") Long id) {
        return new PageVo("1","客户端降级-服务端宕机了。");
    }
}
