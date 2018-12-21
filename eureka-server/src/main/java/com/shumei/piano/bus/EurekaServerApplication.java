package com.shumei.piano.bus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 服务注册中心启动类
 *
 * @author Zhang Yi on 2018/12/21
 * @version Piano V1.0.0
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
}
