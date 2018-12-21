package com.shumei.piano.bus.handler;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 事件接收通道定义
 *
 * @author Zhang Yi on 2018/12/20
 * @version Piano V1.0.0
 */
public interface EventSink
{
    /**
     * 事件接收通道名称
     */
    String INPUT = "eventInput";

    /**
     * 获取事件接收通道
     *
     * @return 事件接收通道
     * @author Zhang Yi on 2018/12/20
     */
    @Input(INPUT)
    SubscribableChannel input();
}
