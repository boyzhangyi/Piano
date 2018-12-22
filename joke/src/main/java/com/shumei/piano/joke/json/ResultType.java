package com.shumei.piano.joke.json;

/**
 * Json数据返回状态
 *
 * @author Zhang Yi on 2018/9/23
 * @version Piano 1.0.0
 */
public enum ResultType
{
    /**
     * 成功
     */
    SUCCESS(0),

    /**
     * 失败
     */
    FAILURE(1);

    private int code;

    /**
     * 默认构造方法
     *
     * @param code 状态码
     */
    ResultType(int code)
    {
        this.code = code;
    }

    public int getCode()
    {
        return code;
    }
}
