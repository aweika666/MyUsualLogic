package com.aweika.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Michael
 * @date: 2020/7/31
 * @description: 主题交换机
 */
@Configuration
public class TopicRabbitConfig {

    @Bean
    public Queue firstTopicQueue() {
        return new Queue("firstTopicQueue");
    }

    @Bean
    public Queue secondTopicQueue() {
        return new Queue("secondTopicQueue");
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }


    //将firstQueue和topicExchange绑定,而且绑定的键值为topic.man
    //这样只要是消息携带的路由键是topic.man,才会分发到该队列
    @Bean
    Binding bindingExchangeMessage(Queue firstTopicQueue,TopicExchange topicExchange) {
        return BindingBuilder.bind(firstTopicQueue).to(topicExchange).with("topic.man");
    }

    //将secondQueue和topicExchange绑定,而且绑定的键值为用上通配路由键规则topic.#
    // 这样只要是消息携带的路由键是以topic.开头,都会分发到该队列
    @Bean
    Binding bindingExchangeMessage2(Queue secondTopicQueue,TopicExchange topicExchange) {
        return BindingBuilder.bind(secondTopicQueue).to(topicExchange).with("topic.#");
    }
}
