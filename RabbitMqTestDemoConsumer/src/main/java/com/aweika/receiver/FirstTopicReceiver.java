package com.aweika.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.util.Map;

/**
 * @author: Michael
 * @date: 2020/7/31
 * @description:
 */
//@Component
@RabbitListener(queues = "firstTopicQueue")
public class FirstTopicReceiver {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("FirstTopicReceiver消费者收到消息  : " + testMessage.toString());
    }
}
