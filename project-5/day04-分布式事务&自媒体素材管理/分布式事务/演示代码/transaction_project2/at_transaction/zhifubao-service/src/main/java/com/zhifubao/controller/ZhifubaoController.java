package com.zhifubao.controller;

import com.zhifubao.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * @作者 itcast
 * @创建日期 2020/7/23 16:57
 **/
@RestController
@RequestMapping("account")
public class ZhifubaoController {
    @Autowired
    AccountService accountService;

    @GetMapping("{username}")
    public String payToYuebao(@PathVariable String username, @RequestParam BigDecimal amount){
        int i = accountService.payToYuebao(username, amount);
        return "转账操作已完成";
    }
}
