package com.zhifubao.dao.impl;

import com.zhifubao.dao.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * @作者 itcast
 * @创建日期 2020/7/23 16:18
 **/
@Repository
public class AccountMapperImpl implements AccountMapper {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public int minusAmount(String username, BigDecimal amount) {
        // 支付宝帐户 减指定金额
        return jdbcTemplate.update("update t_account set amount = amount-? where username = ?", amount, username);
    }
}
