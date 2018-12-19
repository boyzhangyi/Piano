package com.shumei.piano.bus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 聚合数据平台笑话大全微服务
 *
 * @author Eden Zhang 2018/9/23
 * @version Piano V1.0.1
 */
@SpringBootApplication
public class JokeServiceApplication
{
    /**
     * 笑话大全微服务启动入口
     *
     * @author Eden Zhang 2018/9/23
     */
    public static void main(String[] args)
    {
        SpringApplication.run(JokeServiceApplication.class, args);
    }
}
