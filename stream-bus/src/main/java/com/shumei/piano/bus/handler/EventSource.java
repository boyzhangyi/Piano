package com.shumei.piano.bus.handler;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 事件发布通道定义
 *
 * @author Zhang Yi on 2018/12/20
 * @version Piano 1.0.0
 */
public interface EventSource
{
    /**
     * 事件发布通道名称
     */
    String OUTPUT = "eventOutput";

    /**
     * 获取事件发布通道
     *
     * @return 事件发布通道
     * @author Zhang Yi on 2018/12/20
     */
    @Output(OUTPUT)
    MessageChannel output();
}
