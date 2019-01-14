package com.shumei.piano.bus.handler.receiver;

import com.shumei.piano.bus.event.DataCollectEvent;
import com.shumei.piano.bus.handler.EventHandler;
import com.shumei.piano.bus.handler.EventSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * 数据采集事件接收器
 *
 * @author Zhang Yi on 2018/12/20
 * @version Piano 1.0.0
 */
@EnableBinding(EventSink.class)
public class DataCollectEventReceiver
{
    private static final Logger LOGGER = LoggerFactory.getLogger(DataCollectEventReceiver.class);

    /**
     * 处理数据采集事件
     *
     * @param event 数据采集事件
     * @author Zhang Yi on 2018/12/20
     */
    @EventHandler(DataCollectEvent.class)
    public void handleEvent(DataCollectEvent event)
    {
        LOGGER.info("Receive a bus event: {}", event);
    }
}
