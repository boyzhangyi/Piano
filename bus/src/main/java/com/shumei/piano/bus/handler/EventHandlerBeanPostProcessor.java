package com.shumei.piano.bus.handler;

import com.shumei.piano.bus.event.BusEvent;
import com.shumei.piano.bus.event.Event;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binding.StreamListenerAnnotationBeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 重写StreamListener注解初始化配置器
 *
 * @author Zhang Yi on 2018/12/20
 * @version Piano 1.0.0
 */
public class EventHandlerBeanPostProcessor extends StreamListenerAnnotationBeanPostProcessor
{
    /**
     * 用于允许Spring Cloud Stream Binder把事件路由到匹配的方法上的SpEL表达式
     */
    private static final String EVENT_SPEL_PATTERN = "payload.eventType=='%s";

    /**
     * 在此Bean中自定义Processor，然后把eventType属性转成condition表达式
     *
     * @param originalAnnotation 事件驱动注解
     * @param annotatedMethod 添加事件驱动注解的方法
     * @return 返回更新condition属性后的StreamListener注解对象
     * @author Zhang Yi on 2018/12/20
     */
    protected StreamListener postProcessAnnotation(StreamListener originalAnnotation, Method annotatedMethod)
    {
        Map<String, Object> attributes = new HashMap<String, Object>();

        EventHandler eventHandler = AnnotationUtils.findAnnotation(annotatedMethod, EventHandler.class);
        if (eventHandler == null)
        {
            return originalAnnotation;
        }

        Class<? extends BusEvent> eventClass = eventHandler.eventClass();
        Event event = AnnotationUtils.findAnnotation(eventClass, Event.class);
        Assert.isTrue(event != null, "No eventClass name is configured. Use the @Event value.");

        String eventType = event.value();
        Assert.isTrue(StringUtils.isNotEmpty(eventType), "The @Event annotation must have the eventClass name as value.");

        String spelExpression = String.format(EVENT_SPEL_PATTERN, eventType);
        attributes.put("condition", spelExpression);
        attributes.put("target", EventSink.INPUT);
        return AnnotationUtils.synthesizeAnnotation(attributes, StreamListener.class, annotatedMethod);
    }
}
