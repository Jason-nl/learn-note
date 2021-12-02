package com.zhifubao.dao;


import java.math.BigDecimal;

public interface AccountMapper {
    int minusAmount(String username, BigDecimal amount);
}
