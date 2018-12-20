package com.shumei.piano.bus.handler.receiver;

import com.shumei.piano.bus.event.DataCollectEvent;
import com.shumei.piano.bus.handler.EventHandler;
import com.shumei.piano.bus.handler.EventSink;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * Class/Interface/Enum Description
 *
 * @author Zhang Yi on 2018/12/20
 * @version Piano V1.0.0
 */
@EnableBinding(EventSink.class)
public class TestEventReceiver
{
    @EventHandler(DataCollectEvent.class)
    public void handDataCollectEvent(DataCollectEvent event)
    {

    }
}
