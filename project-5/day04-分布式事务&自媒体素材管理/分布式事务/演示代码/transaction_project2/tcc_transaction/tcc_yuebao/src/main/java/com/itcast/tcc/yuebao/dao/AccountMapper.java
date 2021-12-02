package com.itcast.tcc.yuebao.dao;


import java.math.BigDecimal;

public interface AccountMapper {

    // 余额宝 加钱操作 分为3步

    // 预加钱阶段
    int tryAddAmount(String username, BigDecimal amount);
    // 确认加钱
    int confirmAddAmount(String username, BigDecimal amount);
    // 取消加钱
    int cancelAddAmount(String username, BigDecimal amount);


}
