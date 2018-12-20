package com.shumei.piano.bus.handler;

import com.shumei.piano.bus.event.BusEvent;
import org.springframework.core.annotation.AliasFor;

/**
 * 事件处理注解
 *
 * @author Zhang Yi on 2018/12/20
 * @version Piano V1.0.0
 */
public @interface EventHandler
{
    @AliasFor("eventClass")
    Class<? extends BusEvent> value() default BusEvent.class;

    @AliasFor("value")
    Class<? extends BusEvent> eventClass() default BusEvent.class;
}
