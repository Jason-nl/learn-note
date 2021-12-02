package com.itcast.mq.zhifubao.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 本地消息表
 * @作者 itcast
 * @创建日期 2020/7/24 9:02
 **/
@Data
public class Message {
    private String msgId;
    private Integer msgStatus;
    private String username;
    private Integer amount;
    private Date createTime;
    private Date updateTime;
}
