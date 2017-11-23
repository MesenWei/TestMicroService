package com.mesen.api.user.fallbackgood;

import com.mesen.api.user.interfacecontroller.HystrixUserFeignClient3;
import com.mesen.commons.fallbackfactory.ExceptionAutowired;
import com.mesen.vo.PageVo;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * FallbackFactory机制实现断路器的服务降级效果。
 *
 * Created by maosheng on 2017/11/22
 */
@Component
public class HystrixFallbackFactory implements FallbackFactory<HystrixUserFeignClient3>{
    @Override
    public HystrixUserFeignClient3 create(Throwable throwable) {

        return new HystrixUserFeignClient3(){

            @Override
            public PageVo getUserListBad2(Long id) {
                return ExceptionAutowired.autoWired(Optional.of(throwable));
            }

            @Override
            public PageVo getUserListBad3(Long id) {
                return ExceptionAutowired.autoWired(Optional.of(throwable));
            }
        };
    }
}
