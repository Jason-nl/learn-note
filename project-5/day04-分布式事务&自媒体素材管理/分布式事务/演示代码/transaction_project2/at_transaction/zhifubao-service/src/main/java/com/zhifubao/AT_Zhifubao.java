package com.zhifubao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @作者 itcast
 * @创建日期 2020/7/23 16:47
 **/
@SpringBootApplication
public class AT_Zhifubao {
    public static void main(String[] args) {
        SpringApplication.run(AT_Zhifubao.class,args);
    }
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
