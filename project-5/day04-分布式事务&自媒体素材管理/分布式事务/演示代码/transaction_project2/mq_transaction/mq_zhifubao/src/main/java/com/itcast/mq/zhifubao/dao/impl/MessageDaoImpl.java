package com.itcast.mq.zhifubao.dao.impl;


import com.itcast.mq.zhifubao.dao.MessageDao;
import com.itcast.mq.zhifubao.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @作者 itcast
 * @创建日期 2020/7/24 9:05
 **/
@Repository
public class MessageDaoImpl implements MessageDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public int updateMessage(Message message) {
        return jdbcTemplate.update("update t_message set msg_status =? where msg_id=?",new Object[]{message.getMsgStatus(),message.getMsgId()});
    }
    @Override
    public int addMessage(Message message) {
        return jdbcTemplate.update("insert into t_message(msg_id,amount,msg_status,username) values (?,?,?,?)",message.getMsgId(),message.getAmount(),message.getMsgStatus(),message.getUsername());
    }
    @Override
    public List<Message> queryMessageByState(int state) {
        List<Message> list = jdbcTemplate.query("select msg_id as msgId,amount,msg_status as msgStatus,username from t_message where msg_status= ?", new Object[]{state}, new RowMapper<Message>() {
            @Override
            public Message mapRow(ResultSet resultSet, int i) throws SQLException {
                Message msgObj = new Message();
                msgObj.setMsgId(resultSet.getString("msgId"));
                msgObj.setAmount(resultSet.getInt("amount"));
                msgObj.setUsername(resultSet.getString("username"));
                msgObj.setMsgStatus(resultSet.getInt("msgStatus"));
                return msgObj;
            }
        });
        return list;
    }
}
