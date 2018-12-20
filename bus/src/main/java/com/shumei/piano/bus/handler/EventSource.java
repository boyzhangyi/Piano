package com.shumei.piano.bus.handler;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Class/Interface/Enum Description
 *
 * @author Zhang Yi on 2018/12/20
 * @version Piano V1.0.0
 */
public interface EventSource
{
    String OUTPUT = "eventOutput";

    @Output(OUTPUT)
    MessageChannel output();
}
