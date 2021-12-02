package com.yuebao.controller;

import com.yuebao.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * @作者 itcast
 * @创建日期 2020/7/23 15:56
 **/
@RestController
@RequestMapping("account")
public class AccountController {
    @Autowired
    AccountService accountService;
    @PostMapping("{username}")
    public boolean updateAccount(@PathVariable("username")String username, @RequestParam BigDecimal amount){
        // 调用支付宝扣减金额接口
        int i = accountService.addAmount(username, amount);
        // 转账中   一般会在24小时内完成
        return i>0?true:false;
    }
}
