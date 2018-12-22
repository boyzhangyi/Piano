package com.shumei.piano.joke.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shumei.piano.joke.json.JsonResult;
import com.shumei.piano.joke.json.ResultType;
import com.shumei.piano.joke.json.juhe.JokeResult;
import com.shumei.piano.joke.json.juhe.JuheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * 聚合数据平台笑话大全服务
 *
 * @author Zhang Yi on 2018/9/23
 * @version Piano 1.0.0
 */
@RestController
@RequestMapping(value = "joke")
public class JokeController
{
    private static final String APP_KEY = "e2e20e59e3874c91bf0100cc1f66ddd0";

    private Gson gson = new Gson();

    @Autowired
    private RestTemplate template;

    @Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }

    /**
     * 按照更新时间获取笑话
     *
     * @param pageNum  分页页码
     * @param pageSize 每页数量
     * @param sort 类型，desc:指定时间之前发布的，asc:指定时间之后发布的
     * @param time 时间戳（10位），如：1418816972
     * @return 笑话
     * @author Zhang Yi on 2018/9/23
     */
    @RequestMapping(value = "getSortedJokes", method = RequestMethod.GET)
    public JsonResult<List<JokeResult>> getSortedJokes(Integer pageNum, Integer pageSize, String sort, String time)
    {
        JsonResult<List<JokeResult>> jokeResult = new JsonResult<List<JokeResult>>();
        jokeResult.setStatus(ResultType.FAILURE.getCode());

        String response = template.getForObject(
                "http://v.juhe.cn/joke/content/list.php?key={key}&page={page}&pagesize={pagesize}&sort={sort}&time={timestamp}",
                String.class, APP_KEY, pageNum, pageSize, sort, time);

        Type resultType = new TypeToken<JuheResult<Map<String, List<JokeResult>>>>()
        {
        }.getType();
        JuheResult<Map<String, List<JokeResult>>> juheResult = gson.fromJson(response, resultType);
        if (juheResult == null)
        {
            jokeResult.setErrorMsg("聚合数据服务异常");
            return jokeResult;
        }

        if (juheResult.getErrorCode() != 0)
        {
            jokeResult.setErrorMsg(juheResult.getReason());
            return jokeResult;
        }

        jokeResult.setResult(juheResult.getResult().get("data"));
        jokeResult.setStatus(ResultType.SUCCESS.getCode());
        return jokeResult;
    }

    /**
     * 分页获取最近的笑话
     *
     * @param pageNum  分页页码
     * @param pageSize 每页数量
     * @return 最近的笑话
     * @author Zhang Yi on 2018/9/23
     */
    @RequestMapping(value = "getLatestJokes", method = RequestMethod.GET)
    public JsonResult<List<JokeResult>> getLatestJokes(Integer pageNum, Integer pageSize)
    {
        JsonResult<List<JokeResult>> jokeResult = new JsonResult<List<JokeResult>>();
        jokeResult.setStatus(ResultType.FAILURE.getCode());

        String response = template.getForObject(
                "http://v.juhe.cn/joke/content/text.php?key={key}&page={page}&pagesize={pagesize}",
                String.class, APP_KEY, pageNum, pageSize);

        Type resultType = new TypeToken<JuheResult<Map<String, List<JokeResult>>>>()
        {
        }.getType();
        JuheResult<Map<String, List<JokeResult>>> juheResult = gson.fromJson(response, resultType);
        if (juheResult == null)
        {
            jokeResult.setErrorMsg("聚合数据服务异常");
            return jokeResult;
        }

        if (juheResult.getErrorCode() != 0)
        {
            jokeResult.setErrorMsg(juheResult.getReason());
            return jokeResult;
        }

        jokeResult.setResult(juheResult.getResult().get("data"));
        jokeResult.setStatus(ResultType.SUCCESS.getCode());
        return jokeResult;
    }

    /**
     * 随机获取笑话
     *
     * @return 笑话
     * @author Zhang Yi on 2018/9/23
     */
    @RequestMapping(value = "getRandomJokes", method = RequestMethod.GET)
    public JsonResult<List<JokeResult>> getRandomJokes()
    {
        JsonResult<List<JokeResult>> jokeResult = new JsonResult<List<JokeResult>>();
        jokeResult.setStatus(ResultType.FAILURE.getCode());

        String response = template.getForObject("http://v.juhe.cn/joke/randJoke.php?key={key}", String.class, APP_KEY);

        Type resultType = new TypeToken<JuheResult<Map<String, List<JokeResult>>>>()
        {
        }.getType();
        JuheResult<Map<String, List<JokeResult>>> juheResult = gson.fromJson(response, resultType);
        if (juheResult == null)
        {
            jokeResult.setErrorMsg("聚合数据服务异常");
            return jokeResult;
        }

        if (juheResult.getErrorCode() != 0)
        {
            jokeResult.setErrorMsg(juheResult.getReason());
            return jokeResult;
        }

        jokeResult.setResult(juheResult.getResult().get("data"));
        jokeResult.setStatus(ResultType.SUCCESS.getCode());
        return jokeResult;
    }
}
