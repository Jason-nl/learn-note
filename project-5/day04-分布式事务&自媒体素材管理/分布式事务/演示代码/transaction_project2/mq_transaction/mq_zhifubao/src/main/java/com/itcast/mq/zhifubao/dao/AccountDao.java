package com.itcast.mq.zhifubao.dao;


import java.math.BigDecimal;

public interface AccountDao {
    int minusAmount(String username, BigDecimal amount);
}
