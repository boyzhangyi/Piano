package com.shumei.piano.autoconfigure;

import com.shumei.piano.bus.handler.EventHandlerBeanPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.cloud.stream.config.BindingServiceConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(BindingServiceConfiguration.class)
public class EventHandlerAutoConfiguration
{
    @Bean(BindingServiceConfiguration.STREAM_LISTENER_ANNOTATION_BEAN_POST_PROCESSOR_NAME)
    public BeanPostProcessor streamListenerAnnotationBeanPostProcessor()
    {
        return new EventHandlerBeanPostProcessor();
    }
}
