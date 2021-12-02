package com.itcast.mq.zhifubao.timer;

import com.itcast.mq.zhifubao.constant.MessageState;
import com.itcast.mq.zhifubao.dao.MessageDao;
import com.itcast.mq.zhifubao.pojo.Message;
import com.itcast.mq.zhifubao.rabbitmq.RabbitmqSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @作者 itcast
 * @创建日期 2020/7/24 9:31
 **/
@Slf4j
@Component
public class ScheduledService {
    @Autowired
    MessageDao messageDao;
    @Autowired
    RabbitmqSender rabbitmqSender;
    @Scheduled(cron = "0/20 * * * * ?")
    public void retrySendMsg(){
        log.info("定时器触发 检测未被消费消息");
        List<Message> messages = messageDao.queryMessageByState(MessageState.NO_CONSUMED);
        log.info("定时器触发 检测未被消费消息数量==> {}",( messages==null? 0 : messages.size()));
        for (Message message : messages) {
            log.info("重发消息======>");
            rabbitmqSender.sendMessage(MessageState.PAY_EXCHANGE,"zhifubao.pay",message);
        }
    }
}
