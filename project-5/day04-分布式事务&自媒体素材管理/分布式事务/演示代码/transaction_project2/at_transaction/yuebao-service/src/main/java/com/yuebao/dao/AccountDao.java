package com.yuebao.dao;


import java.math.BigDecimal;

public interface AccountDao {
    int addAmount(String username, BigDecimal amount);
}
