package com.itcast.mq.yuebao.dao.impl;

import com.itcast.mq.yuebao.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
/**
 * @作者 itcast
 * @创建日期 2020/7/23 16:36
 **/
@Repository
public class AccountDaoImpl implements AccountDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public int addAmount(String username, BigDecimal amount) {
        // 余额宝帐户 减指定金额
        return jdbcTemplate.update("update t_account set amount = amount+? where username = ?", amount, username);
    }
}
