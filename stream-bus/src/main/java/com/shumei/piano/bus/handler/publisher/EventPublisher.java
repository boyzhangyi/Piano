package com.shumei.piano.bus.handler.publisher;

import com.shumei.piano.bus.event.BusEvent;
import com.shumei.piano.bus.event.Event;
import com.shumei.piano.bus.handler.EventSource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.util.Assert;

/**
 * 事件发布
 *
 * @author Zhang Yi on 2018/12/20
 * @version Piano 1.0.0
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
     * @param busEvent 事件类型
     * @author Zhang Yi on 2018/12/20
     */
    public void publish(BusEvent busEvent)
    {
        LOGGER.info("Publish a bus event: {}", busEvent);

        // 从事件类型中提取事件驱动注解
        Event eventAnnotation = AnnotationUtils.findAnnotation(busEvent.getClass(), Event.class);
        Assert.isTrue(eventAnnotation != null, "No eventClass name is configured. Use the @Event annotation.");

        // 从事件驱动注解提取事件类型
        String eventType = eventAnnotation.value();
        Assert.isTrue(StringUtils.isNotEmpty(eventType), "No eventClass name is configured. Use the @Event value.");

        // 设置事件类型，供事件接收方解析
        busEvent.setEventType(eventType);
        Message message = MessageBuilder.withPayload(busEvent).setPriority(DEFAULT_PRIORITY).build();
        source.output().send(message, EVENT_PUBLISH_TIMEOUT);
    }
}
