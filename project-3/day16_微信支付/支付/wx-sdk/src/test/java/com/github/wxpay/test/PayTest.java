package com.github.wxpay.test;

import com.github.wxpay.sdk.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 虎哥
 */
public class PayTest {

    private WXPay wxPay;

    @Before
    public void setup() throws Exception{
        // 准备WXPay工具，主要准备AppID、MchID、密钥（签名）
        WXPayConfigImpl payConfig = new WXPayConfigImpl();
        payConfig.setAppID("wx8397f8696b538317");
        payConfig.setMchID("1473426802");
        payConfig.setKey("T6m9iK73b0kn9g5v426MKfHQH7X8rKwb");
        // 创建wxPay工具
        this.wxPay = new WXPay(payConfig, "http://www.hello.com");
    }

    /**
     * 统一下单
     */
    @Test
    public void testUnifiedOrder(){
        // 5个请求参数：
        Map<String, String> data = new HashMap<String, String>();
        data.put("body", "腾讯充值中心-QQ会员充值");
        data.put("out_trade_no", "2022414151012");
        data.put("total_fee", "1");
        data.put("spbill_create_ip", "123.12.12.123");
        data.put("trade_type", "NATIVE");  // 此处指定为扫码支付

        try {
            // 下单
            Map<String, String> resp = wxPay.unifiedOrder(data);
            System.out.println(resp);

            boolean isValid = WXPayUtil.isSignatureValid(resp, "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb", WXPayConstants.SignType.HMACSHA256);

            System.out.println("isValid = " + isValid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
