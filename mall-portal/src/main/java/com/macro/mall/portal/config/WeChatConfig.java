package com.macro.mall.portal.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * author:YuJie
 * create:  2024-02-14 22:35
 * Description:微信支付配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechatpay")
public class WeChatConfig {

    /**
     * 应用ID
     */
    private String appId;
    /**
     * 商户ID
     */
    private String mchId;
    /**
     * 密钥
     */
    private String appSecret;
    /**
     * 子商户号
     */
    private String subMchId;
    /**
     * 回调地址
     */
    private String notifyUrl;

    /**
     * 商户API私钥
     */
    private String privateKey;
    /**
     * 商户证书序列号
     */
    private String merchantSerialNumber;
    /**
     * 商户APIV3密钥
     */
    private String apiV3Key;

    /**
     * 请求使用的格式
     */
    private String format = "JSON";

    /**
     * 请求使用的编码格式
     */
    private String charset = "UTF-8";

    /**
     * 生成签名字符串所使用的签名算法类型
     */
    private String signType = "RSA2";
}
