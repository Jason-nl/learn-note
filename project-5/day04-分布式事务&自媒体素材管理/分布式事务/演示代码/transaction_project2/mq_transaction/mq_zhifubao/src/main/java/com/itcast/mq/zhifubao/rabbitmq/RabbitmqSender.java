package com.itcast.mq.zhifubao.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import com.itcast.mq.zhifubao.pojo.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @作者 itcast
 * @创建日期 2020/7/24 9:53
 **/
@Slf4j
@Component
public class RabbitmqSender implements RabbitTemplate.ConfirmCallback {
    private RabbitTemplate rabbitTemplate;

    public RabbitmqSender(RabbitTemplate rabbitTemplate) {
        // 设置确认回调
        rabbitTemplate.setConfirmCallback(this);
        // 设置未投放到指定队列回调
//        rabbitTemplate.setReturnCallback(this);
        this.rabbitTemplate = rabbitTemplate;
    }
    /**
     * 封装发送消息的方法
     * @param exchange
     * @param routekey
     * @param message
     */
    public void sendMessage(String exchange, String routekey, Message message){
        try {
            log.info("发送消息给余额宝");
            rabbitTemplate.convertAndSend(exchange,routekey, JSONObject.toJSONString(message));
        } catch (AmqpException e) {
            log.error("发送消息给余额宝失败 ==> {}",e.getMessage());
        }
    }
    /**
     * 确认回调的处理方法
     * @param correlationData
     * @param b
     * @param s
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if(b){
            log.info("发送消息给余额宝 成功");
        }else {
            log.error("发送消息给余额宝 失败 ==> {}",s);
        }
    }
}
