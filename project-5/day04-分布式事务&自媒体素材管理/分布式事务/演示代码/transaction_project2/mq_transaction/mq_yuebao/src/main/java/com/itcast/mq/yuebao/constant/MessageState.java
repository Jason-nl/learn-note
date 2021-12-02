package com.itcast.mq.yuebao.constant;

/**
 * @作者 itcast
 * @创建日期 2020/7/24 9:35
 **/
public class MessageState {
    public static final Integer HAS_CONSUMED = 1;
    public static final Integer NO_CONSUMED = 0;
    public static final String PAY_YEB_QUEUE = "pay_to_yuebao";
    public static final String PAY_YEB_QUEUE_CALLBACK = "pay_to_yuebao_callback";

    public static final String PAY_EXCHANGE = "pay_exchange";
}
