package com.shumei.piano.bus.handler.publisher;

import com.shumei.piano.bus.event.BusEvent;
import com.shumei.piano.bus.handler.EventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

/**
 * Class/Interface/Enum Description
 *
 * @author Zhang Yi on 2018/12/20
 * @version Piano V1.0.0
 */
@EnableBinding(EventSource.class)
public class EventPublisher
{
    private static final Logger LOGGER = LoggerFactory.getLogger(EventPublisher.class);

    /**
     * 事件默认优先级
     */
    private static final int DEFAULT_PRIORITY = 100;

    /**
     * 事件发布默认超时时间（10秒）
     */
    private static final long EVENT_PUBLISH_TIMEOUT = 10 * 1000;

    @Autowired
    private EventSource source;

    /**
     * 事件发布
     *
     * @param event 事件类型
     * @author Zhang Yi on 2018/12/20
     */
    public void publish(BusEvent event)
    {
        LOGGER.info("Publish a {}", event);
        
        Message message = MessageBuilder.withPayload(event).setPriority(DEFAULT_PRIORITY).build();
        source.output().send(message, EVENT_PUBLISH_TIMEOUT);
    }
}
