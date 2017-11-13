package com.mesen.config;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {
    /**
     * feign默认的契约为SpringMvcContract，所以我们可以正常使用springmvc的注解，
     * 换成feign的契约后，我们就只能使用feign的注解了，否则会报错。
     *
     * @return
     */
    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }

}
