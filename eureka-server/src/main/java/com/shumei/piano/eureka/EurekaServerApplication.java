package com.shumei.piano.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 服务注册中心启动类
 *
 * @author Zhang Yi on 2018/12/21
 * @version Piano 1.0.0
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication
{
    /**
     * 服务注册中心启动入口
     *
     * @param args 服务注册中心启动参数
     * @author Zhang Yi on 2018/12/21
     */
    public static void main(String[] args)
    {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

    /**
     * Web安全配置
     *
     * @author Zhang Yi on 2018/12/23
     * @version Piano 1.0.0
     */
    @Configuration
    public static class WebSecurityConfiguration extends WebSecurityConfigurerAdapter
    {
        /**
         * Web安全配置加载
         *
         * @param httpSecurity Http安全策略
         * @throws Exception 异常信息
         * @author Zhang Yi on 2018/12/23
         */
        @Override
        protected void configure(HttpSecurity httpSecurity)
                throws Exception
        {
            httpSecurity.authorizeRequests().anyRequest().permitAll().and().csrf().disable();
        }
    }
}
