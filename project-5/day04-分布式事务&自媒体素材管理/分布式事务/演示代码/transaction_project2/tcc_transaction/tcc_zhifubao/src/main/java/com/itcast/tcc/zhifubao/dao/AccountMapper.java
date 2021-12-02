package com.itcast.tcc.zhifubao.dao;


import java.math.BigDecimal;

public interface AccountMapper {

    // 余额宝 加钱操作 分为3步

    // 预加钱阶段
    int tryMinusAmount(String username, BigDecimal amount);
    // 确认加钱
    int confirmMinusAmount(String username, BigDecimal amount);
    // 取消加钱
    int cancelMinusAmount(String username, BigDecimal amount);
}
