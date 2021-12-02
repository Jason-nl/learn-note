package com.itcast.mq.zhifubao.controller;

import com.itcast.mq.zhifubao.service.AccountService;
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
    @GetMapping("{username}")
    public String payToYuebao(@PathVariable("username")String username, @RequestParam Integer amount){
        // 调用支付宝扣减金额接口
        accountService.payToYuebao(username, amount);
        // 转账中   一般会在24小时内完成
        return "转账中   一般会在24小时内完成";
    }
}
