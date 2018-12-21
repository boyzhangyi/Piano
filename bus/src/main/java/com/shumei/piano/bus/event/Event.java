package com.shumei.piano.bus.event;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 总线事件注解
 *
 * @author Zhang Yi on 2018/12/20
 * @version Piano V1.0.0
 */
@Component
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Event
{
    /**
     * 获取事件名称
     *
     * @return 事件名称
     * @author Zhang Yi on 2018/12/20
     */
    @AliasFor("eventType")
    String value() default "";

    /**
     * 获取事件名称
     *
     * @return 事件名称
     * @author Zhang Yi on 2018/12/20
     */
    @AliasFor("value")
    String eventType() default "";
}
