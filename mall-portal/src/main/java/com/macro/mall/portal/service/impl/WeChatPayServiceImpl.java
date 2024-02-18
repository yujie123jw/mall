package com.macro.mall.portal.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.macro.mall.portal.config.WeChatConfig;
import com.macro.mall.portal.domain.WeChatPayParam;
import com.macro.mall.portal.service.WeChatPayService;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:YuJie
 * @create: 2024-02-15 15:20
 * @Description:
 */
@Slf4j
@Service
public class WeChatPayServiceImpl implements WeChatPayService {

    @Autowired
    private WechatPayHttpClientBuilder wechatPayHttpClientBuilder;
    @Autowired
    private WeChatConfig weChatConfig;


    @Override
    public String pay(WeChatPayParam weChatPayParam) {

        CloseableHttpClient httpClient = wechatPayHttpClientBuilder.build();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi");
        //HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/xdc/apiv2sandbox/pay/micropay");
        httpPost.addHeader("Accept", "application/json");
        httpPost.addHeader("Content-type", "application/json; charset=utf-8");
        String bodyAsString = "";
        Map<String,String> info = new HashMap<>();
        info.put("type","Wap");
        ObjectNode rootNode = objectMapper.createObjectNode();
        rootNode.put("mchid", weChatConfig.getMchId())
                .put("appid", weChatConfig.getAppId())
                .put("description", weChatPayParam.getSubject())
                .put("notify_url", weChatConfig.getNotifyUrl())
                .put("out_trade_no", weChatPayParam.getOutTradeNo());
        rootNode.putObject("amount")
                .put("total", weChatPayParam.getTotalAmount())
                .put("currency", "CNY");
        rootNode.putObject("payer")
                .put("openid","oqVnH64Nfgm1pTxT-ohgCd9yQhXU");
//                .putPOJO("h5_info", info);
        try {
            objectMapper.writeValue(bos, rootNode);
            httpPost.setEntity(new StringEntity(bos.toString("UTF-8"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            bodyAsString = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            log.error("微信支付请求失败", e);
        }
        return bodyAsString;

    }

    @Override
    public String notify(Map<String, String> params) {
        return null;
    }

    @Override
    public String query(String outTradeNo, String tradeNo) {
        return null;
    }
}
