package com.itcast.mq.zhifubao.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import com.itcast.mq.zhifubao.constant.MessageState;
import com.itcast.mq.zhifubao.dao.MessageDao;
import com.itcast.mq.zhifubao.pojo.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @作者 itcast
 * @创建日期 2020/7/24 11:03
 **/
@Component
@Slf4j
public class RabbitmqListener {
    @Autowired
    MessageDao messageDao;
    /**
     * 处理消费端 的确认反馈消息
     * 收到消息后 将本地消息表消息设置已确认
     * @param messageJson
     */
    @RabbitListener(queues = MessageState.PAY_YEB_QUEUE_CALLBACK)
    public void applyPayCallback(String messageJson){
        JSONObject msgObj = JSONObject.parseObject(messageJson);
        String msgId = msgObj.getString("msgId");
        Boolean ack = msgObj.getBoolean("ack");
        // 如果存在 全局消息ID  并且 状态为确认
        // 将本地消息表 改为已确认
        if(!StringUtils.isEmpty(msgId) && ack){
            Message message = new Message();
            message.setMsgId(msgId);
            message.setMsgStatus(MessageState.HAS_CONSUMED);
            log.info(" 流水: {} 已确认,全局事务处理完毕",msgId);
            messageDao.updateMessage(message);
        }
    }
}
