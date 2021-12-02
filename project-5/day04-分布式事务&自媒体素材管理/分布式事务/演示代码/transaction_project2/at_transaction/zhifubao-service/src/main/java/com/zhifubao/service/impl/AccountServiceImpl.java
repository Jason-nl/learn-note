package com.zhifubao.service.impl;

import com.zhifubao.dao.AccountMapper;
import com.zhifubao.service.AccountService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

/**
 * @作者 itcast
 * @创建日期 2020/7/23 16:08
 **/
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    RestTemplate restTemplate;

    /**
     * 方法涉及分布式事务
     * @param username
     * @param amount
     * @return
     */
    @GlobalTransactional
    @Override
    public int payToYuebao(String username, BigDecimal amount) {
        int i = accountMapper.minusAmount(username, amount);
        log.info("用户 : {} , 支付宝减钱: {}  操作成功" ,username,amount);
        // 远程调用余额宝+钱
        yuebaoAddAmount(username,amount);
        if(amount.intValue() < 100){
            // 制造异常  余额宝操作无法回滚
            System.out.println(1/0);
        }
        return i;
    }
    public boolean yuebaoAddAmount(String username, BigDecimal amount){
        ResponseEntity<Boolean> booleanResponseEntity = restTemplate.postForEntity("http://localhost:9001/account/"+username+"?amount="+amount, null, Boolean.class);
        return booleanResponseEntity.getBody();
    }
}
