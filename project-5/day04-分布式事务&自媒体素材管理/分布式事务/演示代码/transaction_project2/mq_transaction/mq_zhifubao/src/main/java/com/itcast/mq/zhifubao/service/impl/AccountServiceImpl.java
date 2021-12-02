package com.itcast.mq.zhifubao.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.itcast.mq.zhifubao.constant.MessageState;
import com.itcast.mq.zhifubao.dao.AccountDao;
import com.itcast.mq.zhifubao.dao.MessageDao;
import com.itcast.mq.zhifubao.pojo.Message;
import com.itcast.mq.zhifubao.rabbitmq.RabbitmqConfig;
import com.itcast.mq.zhifubao.rabbitmq.RabbitmqSender;
import com.itcast.mq.zhifubao.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @作者 itcast
 * @创建日期 2020/7/23 16:08
 **/
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDao accountDao;
    @Autowired
    MessageDao messageDao;
    @Autowired
    TransactionTemplate transactionTemplate;

    @Autowired
    RabbitmqSender rabbitmqSender;

    // 优化细节, 使用编程事务 控制方法内事务的粒度
    // 防止发消息的操作出错 造成整个回滚
//    @Transactional
    @Override
    public void payToYuebao(String username, Integer amount) {
        // 手动编程事务  保证 减钱  和  添加消息数据到本地消息表 在一个事务中
        Message sendMsg = transactionTemplate.execute((transactionStatus)->{
            Message message = null;
            String msgId = UUID.randomUUID().toString().replaceAll("-","");
            // 1. 支付宝减钱
            int i = accountDao.minusAmount(username, new BigDecimal(amount));
            log.info("用户 : {} , 余额宝加钱: {}  操作成功" ,username,amount);
            // 2. 本地消息表添加事务消息
            if(i>0){
                message = new Message();
                message.setMsgId(msgId);
                message.setMsgStatus(MessageState.NO_CONSUMED);
                message.setUsername(username);
                message.setAmount(amount);
                messageDao.addMessage(message);
            }
            return message;
        });
        // 发消息操作 不需要在事务中，出错后 会有重试机制 重新发送消息
        if(sendMsg!=null){
            // 3. 发送消息到MQ
            rabbitmqSender.sendMessage(MessageState.PAY_EXCHANGE,"zhifubao.pay",sendMsg);
        }
    }

    /**
     * 消费服务的回调方法
     * 暂时只处理消费成功的情况
     * @param param
     */
    @Transactional
    @Override
    public void payToYuebaoCallback(String param) {
        JSONObject jsonObject = JSONObject.parseObject(param);
        String msgId = jsonObject.getString("msgId");
        Message message = new Message();
        message.setMsgStatus(MessageState.HAS_CONSUMED);
        message.setMsgId(msgId);
        messageDao.updateMessage(message);
    }
}
