package com.shumei.piano.eureka;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring Security默认开启了所有CSRF攻击防御，需要禁用/eureka的防御
 *
 * @author Zhang Yi on 2019/1/5
 * @version Piano 1.0.0
 */
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter
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
        httpSecurity.csrf().ignoringAntMatchers("/eureka/**")
                    .and().authorizeRequests().anyRequest().authenticated()
                    .and().httpBasic();
        super.configure(httpSecurity);
    }
}
