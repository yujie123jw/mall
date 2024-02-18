package com.macro.mall.portal.service;

import com.macro.mall.portal.domain.WeChatPayParam;

import java.util.Map;

public interface WeChatPayService {


    /**
     * 根据提交参数生成电脑支付页面
     */
    String pay(WeChatPayParam weChatPayParam);

    /**
     * 支付宝异步回调处理
     */
    String notify(Map<String, String> params);

    /**
     * 查询支付宝交易状态
     * @param outTradeNo 商户订单编号
     * @param tradeNo 支付宝交易编号
     * @return 支付宝交易状态
     */
    String query(String outTradeNo, String tradeNo);
}
