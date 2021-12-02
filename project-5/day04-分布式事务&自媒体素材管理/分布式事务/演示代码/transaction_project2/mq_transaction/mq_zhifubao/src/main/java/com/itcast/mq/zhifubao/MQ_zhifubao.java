package com.itcast.mq.zhifubao;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @作者 itcast
 * @创建日期 2020/7/24 9:28
 **/
@SpringBootApplication
@EnableScheduling
@EnableRabbit
public class MQ_zhifubao {
    public static void main(String[] args) {
        SpringApplication.run(MQ_zhifubao.class,args);
    }
}
