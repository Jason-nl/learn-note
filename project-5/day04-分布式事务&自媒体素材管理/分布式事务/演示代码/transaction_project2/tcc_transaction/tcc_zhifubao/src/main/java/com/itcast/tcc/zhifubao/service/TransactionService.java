package com.itcast.tcc.zhifubao.service;

import java.math.BigDecimal;
import java.util.Map;

public interface TransactionService {
    String doTransactionCommit(String username, BigDecimal amount);
}
