package com.shumei.piano.bus.event;

/**
 * 抽象总线事件
 *
 * @author Zhang Yi on 2018/12/20
 * @version Piano 1.0.0
 */
public abstract class BusEvent
{
    private String eventType;

    public String getEventType()
    {
        return eventType;
    }

    public void setEventType(String eventType)
    {
        this.eventType = eventType;
    }
}
