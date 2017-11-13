package com.mesen.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import javax.annotation.Resource;

/**
 * 在使用微服务时，我们的微服务最好是要有认证的，为了方便，我们不会为每一个微服务手动添加认证信息，
 * 故在此定义配置Web认证的类，为所有微服务提供认证设置。
 * 增加了Configuration注解的类，也应当被spring扫描到才会起作用。
 *
 * Created by mesen on 2017.11.11
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 为请求设置用户名密码和角色。
     *
     * @param auth
     * @throws Exception
     */
    @Resource
    public void configGlobal(AuthenticationManagerBuilder auth)throws Exception {
        System.out.println("设置用户名和密码。");
        auth.inMemoryAuthentication().withUser("admin").password("admin123").roles("USER");
    }

    /**
     * 设置session为无状态，默认为无状态。
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("设置session状态为无状态。");
        // 表示所有的访问都必须进行认证处理后才可以正常进行
        http.httpBasic().and().authorizeRequests().anyRequest().fullyAuthenticated();
        // 所有的Rest服务一定要设置为无状态，以提升操作性能
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
