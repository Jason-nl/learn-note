package com.yuebao.service.impl;

import com.yuebao.dao.AccountDao;
import com.yuebao.service.AccountService;
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
    AccountDao accountDao;

    @Transactional  // 事务注解
    @Override
    public int addAmount(String username, BigDecimal amount) {
        int i = accountDao.addAmount(username, amount);
        log.info("用户 : {} , 余额宝加钱: {}  操作成功" ,username,amount);
        return i;
    }
}
