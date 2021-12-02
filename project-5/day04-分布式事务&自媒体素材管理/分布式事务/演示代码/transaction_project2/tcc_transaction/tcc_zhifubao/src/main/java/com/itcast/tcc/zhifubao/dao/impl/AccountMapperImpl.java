package com.itcast.tcc.zhifubao.dao.impl;

import com.itcast.tcc.zhifubao.dao.AccountMapper;
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
    public int tryMinusAmount(String username, BigDecimal amount) {
        String sql = "update t_account set amount=amount-"+amount+",frozen = frozen +"+amount+" where username = '"+username+"'";
        System.out.println(sql);
        return jdbcTemplate.update(sql);
    }
    @Override
    public int confirmMinusAmount(String username, BigDecimal amount) {
        return jdbcTemplate.update("update t_account set frozen=frozen-? where username = ?", amount,username);
    }
    @Override
    public int cancelMinusAmount(String username, BigDecimal amount) {
        return jdbcTemplate.update("update t_account set amount = amount + ?,frozen=frozen-? where username = ?", amount,amount,username);
    }
}
