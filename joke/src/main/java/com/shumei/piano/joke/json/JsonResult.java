package com.shumei.piano.joke.json;

/**
 * Json数据返回对象
 *
 * @author Eden Zhang 2018/9/23
 * @version Piano V1.0.1
 */
public class JsonResult<T>
{
    private Integer status;

    private T result;

    private String errorMsg;

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public T getResult()
    {
        return result;
    }

    public void setResult(T result)
    {
        this.result = result;
    }

    public String getErrorMsg()
    {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg)
    {
        this.errorMsg = errorMsg;
    }
}
