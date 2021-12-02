package com.itcast.mq.zhifubao.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @作者 itcast
 * @创建日期 2020/7/23 16:04
 **/
@Data
public class Account implements Serializable {
    private Integer id;
    private BigDecimal amount; // 帐户余额
    private BigDecimal frozen; // 冻结金额
    private String username; // 用户名
}
