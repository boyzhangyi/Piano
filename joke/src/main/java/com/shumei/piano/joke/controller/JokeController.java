package com.shumei.piano.joke.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("joke")
public class JokeController
{
    @Autowired
    private RestTemplate template;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping(value = "getJokes", method = RequestMethod.GET)
    public String getJokes()
    {
        long timeStampSec = System.currentTimeMillis()/1000;
        String timestamp = String.format("%010d", timeStampSec);
        return template.getForObject(
                "http://v.juhe.cn/joke/content/list.php?key=e2e20e59e3874c91bf0100cc1f66ddd0&page=2&pagesize=10&sort=asc&time={timestamp}",
                String.class, "1418745237");
//        return "Hello World!";
    }
}
