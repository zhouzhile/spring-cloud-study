package com.zzl.study.cloud.jvm.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RabbitMQConfig
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/5/25 16:30
 * @Version 1.0
 **/
@Configuration
public class RabbitMQConfig {
    // 交换机名称
    public static final String ITEM_TOPIC_EXCHANGE = "item_topic_exchange";

    // 队列名称
    public static final String ITEM_QUEUE = "item_queue";

    // 声明队列
    @Bean("item_queue")
    public Queue getItemQueue(){
        return QueueBuilder.durable(ITEM_QUEUE).build();
    }

    // 声明交换机
    @Bean("item_topic_exchange")
    public TopicExchange getTopicExchange(){
        return ExchangeBuilder.topicExchange(ITEM_TOPIC_EXCHANGE).durable(true).build();
    }

    // 绑定队列与交换机
    @Bean
    public Binding itemQueueExchangeBinding(@Qualifier("item_queue")Queue queue, @Qualifier("item_topic_exchange")Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("item.#").noargs();
    }
}
