package com.itcast.tcc.zhifubao.service.impl;

import com.itcast.tcc.zhifubao.dao.AccountMapper;
import com.itcast.tcc.zhifubao.service.AccountService;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean tryMinusAmount(BusinessActionContext actionContext, String username, BigDecimal amount) {
        int i = accountMapper.tryMinusAmount(username, amount);
        log.info("用户 : {} , 支付宝预减钱: {}  预减钱操作成功" ,username,amount);
        return i>0?true:false;
    }
    @Override
    public boolean confirmMinusAmount(BusinessActionContext actionContext) {
        String username = (String)actionContext.getActionContext("username");
        Integer amount1 = (Integer)actionContext.getActionContext("amount");
        BigDecimal amount = new BigDecimal(amount1);

        int i = accountMapper.confirmMinusAmount(username,amount);
        log.info("用户 : {} , 支付宝预减钱: {}  提交操作成功" ,username,amount);
        return i>0?true:false;
    }
    @Override
    public boolean cancelMinusAmount(BusinessActionContext actionContext) {
        String username = (String)actionContext.getActionContext("username");
        Integer amount1 = (Integer)actionContext.getActionContext("amount");
        BigDecimal amount = new BigDecimal(amount1);

        int i = accountMapper.cancelMinusAmount(username,amount);
        log.info("用户 : {} , 支付宝预减钱: {}  回滚操作成功" ,username,amount);
        return i>0?true:false;
    }
}
