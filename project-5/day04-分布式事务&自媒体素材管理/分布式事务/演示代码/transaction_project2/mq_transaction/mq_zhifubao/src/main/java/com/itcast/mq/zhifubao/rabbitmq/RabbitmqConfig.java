package com.itcast.mq.zhifubao.rabbitmq;

import com.itcast.mq.zhifubao.constant.MessageState;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 声明需要的队列  交换器
 * 根据路由 绑定 队列和交换器
 * @作者 itcast
 * @创建日期 2020/7/24 9:53
 **/
@Configuration
public class RabbitmqConfig {
    /**
     * 声明队列
     * @return
     */
    @Bean
    public Queue queueMsg(){
        return new Queue(MessageState.PAY_YEB_QUEUE,true);
    }
    @Bean
    public Queue queueMsgCallback(){
        return new Queue(MessageState.PAY_YEB_QUEUE_CALLBACK,true);
    }
    /**
     * 声明交换器
     * @return
     */
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(MessageState.PAY_EXCHANGE);
    }

    /**
     * 绑定关系
     * @return
     */
    @Bean
    public Binding payBinding(){
        // 创建绑定关系   队列    交换器   绑定路由key
        return new Binding(MessageState.PAY_YEB_QUEUE, Binding.DestinationType.QUEUE,MessageState.PAY_EXCHANGE,"#.pay",null);
    }
    @Bean
    public Binding payCallbackBinding(){
        // 创建绑定关系   队列    交换器   绑定路由key
        return new Binding(MessageState.PAY_YEB_QUEUE_CALLBACK, Binding.DestinationType.QUEUE,MessageState.PAY_EXCHANGE,"#.callback",null);
    }
}
