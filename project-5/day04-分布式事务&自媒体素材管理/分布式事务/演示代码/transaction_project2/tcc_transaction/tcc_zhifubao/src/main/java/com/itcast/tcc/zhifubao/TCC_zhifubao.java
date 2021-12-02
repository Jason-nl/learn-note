package com.itcast.tcc.zhifubao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @作者 itcast
 * @创建日期 2020/7/23 17:30
 **/
@SpringBootApplication
public class TCC_zhifubao {
    public static void main(String[] args) {
        SpringApplication.run(TCC_zhifubao.class,args);
    }


    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
