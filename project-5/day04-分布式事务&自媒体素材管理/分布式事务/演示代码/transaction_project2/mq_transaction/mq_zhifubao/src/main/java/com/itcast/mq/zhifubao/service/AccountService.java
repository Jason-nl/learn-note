package com.itcast.mq.zhifubao.service;

import java.math.BigDecimal;

public interface AccountService {
    void payToYuebao(String username, Integer amount);

    void payToYuebaoCallback(String param);
}
