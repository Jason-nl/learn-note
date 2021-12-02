package com.itcast.tcc.yuebao.dao.impl;

import com.itcast.tcc.yuebao.dao.AccountMapper;
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
    public int tryAddAmount(String username, BigDecimal amount) {
        int update = jdbcTemplate.update("update t_account set frozen = frozen + ? where username = ?", amount, username);
        if(amount.intValue()<100){
            System.out.println("金额小于100 报异常");
            return 0;
        }
        // 余额宝帐户 在冻结字段中加钱
        return update;
    }
    @Override
    public int confirmAddAmount(String username, BigDecimal amount) {
        // 余额宝帐户 真正字段加钱
        return jdbcTemplate.update("update t_account set amount = amount+?,frozen=frozen-? where username = ?", amount, amount,username);
    }
    @Override
    public int cancelAddAmount(String username, BigDecimal amount) {
        // 余额宝帐户 减指定金额
        return jdbcTemplate.update("update t_account set frozen = frozen-? where username = ?", amount, username);
    }
}
