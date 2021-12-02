package com.itcast.tcc.yuebao.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

import java.math.BigDecimal;

/**
 * 余额宝中的服务接口
 */
@LocalTCC
public interface AccountService {
    @TwoPhaseBusinessAction(
            name="yuebao_add",
            commitMethod = "confirmAddAmount",
            rollbackMethod = "cancelAddAmount"
    )
    boolean tryAddAmount(BusinessActionContext actionContext, @BusinessActionContextParameter(paramName = "username")String username, @BusinessActionContextParameter(paramName = "amount")BigDecimal amount);
    boolean confirmAddAmount(BusinessActionContext actionContext);
    boolean cancelAddAmount(BusinessActionContext actionContext);
}
