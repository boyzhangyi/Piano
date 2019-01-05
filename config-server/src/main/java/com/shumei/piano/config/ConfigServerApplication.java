package com.shumei.piano.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 配置中心启动入口
 *
 * @author Zhang Yi on 2018/12/22
 * @version Piano 1.0.0
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication
{
    /**
     * 配置中心启动入口
     *
     * @param args 服务注册中心启动参数
     * @author Zhang Yi on 2018/12/21
     */
    public static void main(String[] args)
    {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
