package com.shumei.piano.autoconfigure;

import com.shumei.piano.bus.handler.EventHandlerBeanPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.cloud.stream.config.BindingServiceConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 事件处理器自动化配置
 *
 * @author Zhang Yi on 2018/12/20
 * @version Piano V1.0.0
 */
@Configuration
@AutoConfigureAfter(BindingServiceConfiguration.class)
public class EventHandlerAutoConfiguration
{
    /**
     * 替换Spring Cloud Stream框架初始换的StreamListenerAnnotationBeanPostProcessor<br>
     * 重写{@link }中postProcessAnnotation方法，将事件类型‘eventType’注入到‘condition’表达式中
     *
     * @return 重写的StreamListenerAnnotationBeanPostProcessor
     * @author Zhang Yi on 2018/12/20
     */
    @Bean(BindingServiceConfiguration.STREAM_LISTENER_ANNOTATION_BEAN_POST_PROCESSOR_NAME)
    public BeanPostProcessor streamListenerAnnotationBeanPostProcessor()
    {
        return new EventHandlerBeanPostProcessor();
    }
}
