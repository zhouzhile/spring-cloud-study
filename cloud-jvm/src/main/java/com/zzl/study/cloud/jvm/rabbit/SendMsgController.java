package com.zzl.study.cloud.jvm.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mq")
public class SendMsgController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/sendmsg")
    public String sendMsg(@RequestParam String key,@RequestParam String msg){
        rabbitTemplate.convertAndSend(RabbitMQConfig.ITEM_TOPIC_EXCHANGE,key,msg);
        return "发送消息成功";
    }
}
