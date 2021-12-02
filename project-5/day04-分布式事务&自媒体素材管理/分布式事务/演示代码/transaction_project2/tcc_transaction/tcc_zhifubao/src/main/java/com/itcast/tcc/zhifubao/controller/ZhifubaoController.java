package com.itcast.tcc.zhifubao.controller;

import com.itcast.tcc.zhifubao.service.TransactionService;
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
    TransactionService transactionService;

    @GetMapping("{username}")
    public String payToYuebao(@PathVariable String username, @RequestParam BigDecimal amount){
        String xid = transactionService.doTransactionCommit(username, amount);
        System.out.println("全局事务ID " + xid);
        // 如果得到全局事务ID  执行提交操作
        // 如果没得到  执行回滚操作
        return "全局处理完毕 全局事务ID===>"+xid;
    }
}
