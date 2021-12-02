package com.itcast.mq.yuebao.service;

import java.math.BigDecimal;

public interface AccountService {
    int addAmount(String username, BigDecimal amount);
}
