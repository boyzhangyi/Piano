package com.shumei.piano.bus.handler;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Class/Interface/Enum Description
 *
 * @author Zhang Yi on 2018/12/20
 * @version Piano V1.0.0
 */
public interface EventSink
{
    String INPUT = "eventInput";

    @Input(INPUT)
    SubscribableChannel input();
}
