package com.itcast.mq.yuebao.listener;

import com.alibaba.fastjson.JSONObject;
import com.itcast.mq.yuebao.constant.MessageState;
import com.itcast.mq.yuebao.pojo.Message;
import com.itcast.mq.yuebao.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;

/**
 * @作者 itcast
 * @创建日期 2020/7/24 10:43
 **/
@Component
@Slf4j
public class MessageListener {

    @Autowired
    AccountService accountService;

    @Autowired
    StringRedisTemplate redisTemplate; // 使用redis处理幂等性

    @Autowired
    TransactionTemplate transactionTemplate; // 编程事务对象

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = MessageState.PAY_YEB_QUEUE)
    public void applyPayMsg(String messageJson){
        log.info("接收到 转账消息请求 消息内容==> {}",messageJson);
        try {
            // 模拟消费处理时间
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 1. 接收传递过来的消息后，需要考虑幂等性
        Message msgObj = JSONObject.parseObject(messageJson, Message.class);
        // 2. redis中查看消息是否已经处理过
        Object handle_pay_msg = redisTemplate.boundHashOps("handle_pay_msg").get(msgObj.getMsgId());
        if(handle_pay_msg!=null){
            // 重复性消息 不在处理
            log.error("重复的余额宝转账消息  已忽略 流水号: {}",msgObj.getMsgId());
            return;
        }
        boolean handleResult = transactionTemplate.execute((state)->{
            int i = accountService.addAmount(msgObj.getUsername(), new BigDecimal(msgObj.getAmount()));
            if(i>0){
                redisTemplate.boundHashOps("handle_pay_msg").put(msgObj.getMsgId(),JSONObject.toJSONString(msgObj));
                return true;
            }
            return false;
        });
        if(handleResult){
            // 处理结束 回发确认消息
            JSONObject ackMsg = new JSONObject();
            ackMsg.put("msgId",msgObj.getMsgId());
            ackMsg.put("ack",true);
            rabbitTemplate.convertAndSend(MessageState.PAY_EXCHANGE,"yuebao.callback",ackMsg.toJSONString());
            log.info("余额宝处理转账完毕 异步回复确认消息到 支付宝 流水ID ==> {}",msgObj.getMsgId());
        }
    }
}
