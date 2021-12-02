package com.itcast.tcc.zhifubao.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

import java.math.BigDecimal;
@LocalTCC
public interface AccountService {

    @TwoPhaseBusinessAction(
            name="zhifubao_add",
            commitMethod = "confirmMinusAmount",
            rollbackMethod = "cancelMinusAmount")
    boolean tryMinusAmount(BusinessActionContext actionContext, @BusinessActionContextParameter(paramName = "username")String username, @BusinessActionContextParameter(paramName = "amount")BigDecimal amount);
    boolean confirmMinusAmount(BusinessActionContext actionContext);
    boolean cancelMinusAmount(BusinessActionContext actionContext);
}
