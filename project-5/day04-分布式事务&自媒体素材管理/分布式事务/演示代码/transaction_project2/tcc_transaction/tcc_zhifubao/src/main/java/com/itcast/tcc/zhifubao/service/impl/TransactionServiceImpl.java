package com.itcast.tcc.zhifubao.service.impl;

import com.itcast.tcc.zhifubao.service.AccountService;
import com.itcast.tcc.zhifubao.service.TransactionService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @作者 itcast
 * @创建日期 2020/7/23 17:49
 **/
@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    AccountService accountService;

    @Autowired
    RestTemplate restTemplate;

    /**
     * 这里创建全局事务，调用每个事务参与者的try方法
     * 如果有一个try失败，直接抛出异常   抛出异常后seata会执行 cancel方法
     * 如果全部try执行成功，    seata会执行 commit方法
     * @param username
     * @param amount
     * @return
     */
    @GlobalTransactional
    @Override
    public String doTransactionCommit(String username, BigDecimal amount) {
        //第一个TCC 事务参与者
        boolean result = accountService.tryMinusAmount(null,username, amount);
        if (!result) {
            throw new RuntimeException("支付宝扣减金额 失败==> 触发回滚");
        }
        //第二个TCC 事务参与者
        result = yuebaoAddAmount(username, amount);
        if (!result) {
            throw new RuntimeException("余额宝增加金额 失败==> 触发回滚");
        }
        return RootContext.getXID();
    }

    public boolean yuebaoAddAmount(String username, BigDecimal amount){
        ResponseEntity<Boolean> booleanResponseEntity = restTemplate.postForEntity("http://localhost:9001/account/" + username + "?amount=" + amount, null, Boolean.class);
        return booleanResponseEntity.getBody();
    }
}
