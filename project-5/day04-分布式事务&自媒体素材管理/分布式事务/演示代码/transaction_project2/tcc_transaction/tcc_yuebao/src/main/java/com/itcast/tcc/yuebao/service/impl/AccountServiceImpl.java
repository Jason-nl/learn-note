package com.itcast.tcc.yuebao.service.impl;

import com.itcast.tcc.yuebao.dao.AccountMapper;
import com.itcast.tcc.yuebao.service.AccountService;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public boolean tryAddAmount(BusinessActionContext actionContext,String username, BigDecimal amount) {
        int i = accountMapper.tryAddAmount(username, amount);
        log.info("用户 : {} , 余额宝预加钱: {}  预加钱操作成功" ,username,amount);
        return i>0?true:false;
    }
    @Override
    public boolean confirmAddAmount(BusinessActionContext actionContext) {
        String username = (String)actionContext.getActionContext("username");
        Integer amount1 = (Integer)actionContext.getActionContext("amount");
        BigDecimal amount = new BigDecimal(amount1);
        int i = accountMapper.confirmAddAmount(username, amount);
        log.info("用户 : {} , 余额宝预加钱: {}  确认操作成功" ,username,amount);
        return i>0?true:false;
    }
    @Override
    public boolean cancelAddAmount(BusinessActionContext actionContext) {
        String username = (String)actionContext.getActionContext("username");
        Integer amount1 = (Integer)actionContext.getActionContext("amount");
        BigDecimal amount = new BigDecimal(amount1);
        int i = accountMapper.cancelAddAmount(username, amount);
        log.info("用户 : {} , 余额宝预加钱: {}  回滚操作成功" ,username,amount);
        return i>0?true:false;
    }


}
