package com.itcast.mq.zhifubao.dao;


import com.itcast.mq.zhifubao.pojo.Message;

import java.util.List;

public interface MessageDao {
    int updateMessage(Message message);

    int addMessage(Message message);

    List<Message> queryMessageByState(int state);
}
