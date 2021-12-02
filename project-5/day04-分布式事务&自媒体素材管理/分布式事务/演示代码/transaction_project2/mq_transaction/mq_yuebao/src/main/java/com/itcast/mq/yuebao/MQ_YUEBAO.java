package com.itcast.mq.yuebao;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @作者 itcast
 * @创建日期 2020/7/24 8:58
 **/
@SpringBootApplication
@EnableRabbit
public class MQ_YUEBAO {
    public static void main(String[] args) {
        SpringApplication.run(MQ_YUEBAO.class,args);
    }
}
