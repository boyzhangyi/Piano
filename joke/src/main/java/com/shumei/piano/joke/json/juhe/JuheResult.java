package com.shumei.piano.joke.json.juhe;

import com.google.gson.annotations.SerializedName;

/**
 * 聚合数据平台返回Json数据对象
 *
 * @author Zhang Yi on 2018/9/23
 * @version Piano 1.0.0
 */
public class JuheResult<T>
{
    /**
     * 错误码
     */
    @SerializedName("error_code")
    private int  errorCode;

    /**
     * 返回说明
     */
    private String reason;

    /**
     * 返回结果集
     */
    private T result;

    public int getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(int errorCode)
    {
        this.errorCode = errorCode;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public T getResult()
    {
        return result;
    }

    public void setResult(T result)
    {
        this.result = result;
    }
}
