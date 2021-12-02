package com.zhifubao.service;

import java.math.BigDecimal;

public interface AccountService {
    int payToYuebao(String username, BigDecimal amount);
}
