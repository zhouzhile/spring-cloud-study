package com.zzl.study.cloud.jvm.rabbit;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName MyListener
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/5/25 16:42
 * @Version 1.0
 **/
@Component
public class MyListener {

    @RabbitListener(queues = "item_queue")
    public void consumerMsg(String message){
        System.out.println("消费者消费的消息:"+message);
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "topic.n1", durable = "false", autoDelete = "true"),
            exchange = @Exchange(value = RabbitMQConfig.ITEM_TOPIC_EXCHANGE, type = ExchangeTypes.TOPIC),
            key = "item.#"))
    public void consumerMsg1(String message){
        System.out.println("消费者1消费的消息:"+message);
    }
}
