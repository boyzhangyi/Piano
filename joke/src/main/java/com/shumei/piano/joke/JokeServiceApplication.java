package com.shumei.piano.joke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 聚合数据平台笑话大全微服务
 *
 * @author Zhang Yi on 2018/9/23
 * @version Piano 1.0.0
 */
@SpringBootApplication
public class JokeServiceApplication
{
    /**
     * 笑话大全微服务启动入口
     *
     * @author Zhang Yi on 2018/9/23
     */
    public static void main(String[] args)
    {
        SpringApplication.run(JokeServiceApplication.class, args);
    }
}
