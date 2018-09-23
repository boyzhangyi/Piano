package com.shumei.piano.joke.json.juhe;

import java.util.Date;

/**
 * 聚合数据平台笑话大全API返回Json数据对象
 *
 * @author Eden Zhang 2018/9/23
 * @version Piano V1.0.1
 */
public class JokeResult
{
    private String hashId;
    private Long unixTime;
    private Date updateTime;

    /**
     * 笑话内容
     */
    private String content;

    public String getHashId()
    {
        return hashId;
    }

    public void setHashId(String hashId)
    {
        this.hashId = hashId;
    }

    public Long getUnixTime()
    {
        return unixTime;
    }

    public void setUnixTime(Long unixTime)
    {
        this.unixTime = unixTime;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }
}
