package com.itcast.mq.zhifubao.constant;

/**
 * 准备好案例中的相关常量
 * 消息确认状态: 0 未确认  1 已确认
 * 使用的队列:
 * 使用的交换器:
 * @作者 itcast
 * @创建日期 2020/7/24 9:35
 **/
public class MessageState {
    // 已确认消费的消息
    public static final Integer HAS_CONSUMED = 1;
    // 未确认消费的消息
    public static final Integer NO_CONSUMED = 0;

    // 通知余额宝转账的队列
    public static final String PAY_YEB_QUEUE = "pay_to_yuebao";
    // 余额宝转正成功的回调队列
    public static final String PAY_YEB_QUEUE_CALLBACK = "pay_to_yuebao_callback";
    // 转账功能 设计的交换器
    public static final String PAY_EXCHANGE = "pay_exchange";
}
