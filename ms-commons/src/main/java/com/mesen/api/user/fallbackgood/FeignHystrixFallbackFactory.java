package com.mesen.api.user.fallbackgood;

import com.mesen.api.user.interfacecontroller.HystrixUserFeignClient3;
import com.mesen.commons.exception.ExceptionAutowired;
import com.mesen.commons.fallbackfactory.ControllerCommonFallback;
import com.mesen.vo.PageVo;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * FallbackFactory机制是Feign提供的，主要在通过Feign访问其他微服务时实现断路器的服务降级效果。
 *
 * Created by maosheng on 2017/11/22
 */
@Component
public class FeignHystrixFallbackFactory extends ExceptionAutowired implements FallbackFactory<HystrixUserFeignClient3>{
    @Override
    public HystrixUserFeignClient3 create(Throwable throwable) {

        return new HystrixUserFeignClient3(){

            @Override
            public PageVo getUserListBad2(Long id) {
                return autoWired(Optional.of(throwable));
            }

            @Override
            public PageVo getUserListBad3(Long id) {
                return autoWired(Optional.of(throwable));
            }
        };
    }
}
