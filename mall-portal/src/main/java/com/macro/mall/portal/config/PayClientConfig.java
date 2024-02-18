package com.macro.mall.portal.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.cert.CertificatesManager;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
/**
 * @auther macrozheng
 * @description 支付宝请求客户端配置
 * @date 2023/9/8
 * @github https://github.com/macrozheng
 */
@Configuration
public class PayClientConfig {


    @Bean
    public AlipayClient alipayClient(AlipayConfig config) {
        return new DefaultAlipayClient(config.getGatewayUrl(), config.getAppId(), config.getAppPrivateKey(), config.getFormat(), config.getCharset(), config.getAlipayPublicKey(), config.getSignType());
    }

    @Bean
    public WechatPayHttpClientBuilder wechatPayHttpClientBuilder(WeChatConfig config) throws Exception {
        PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(new ByteArrayInputStream(config.getPrivateKey().getBytes(StandardCharsets.UTF_8)));
        // 获取证书管理器实例
        CertificatesManager certificatesManager = CertificatesManager.getInstance();
        // 向证书管理器增加需要自动更新平台证书的商户信息
        certificatesManager.putMerchant(config.getMchId(), new WechatPay2Credentials(config.getMchId(), new PrivateKeySigner(config.getMerchantSerialNumber(), merchantPrivateKey)), config.getApiV3Key().getBytes(StandardCharsets.UTF_8));
        // 从证书管理器中获取verifier
        Verifier verifier = certificatesManager.getVerifier(config.getMchId());

        return WechatPayHttpClientBuilder.create()
                .withMerchant(config.getMchId(), config.getMerchantSerialNumber(), merchantPrivateKey)
                .withValidator(new WechatPay2Validator(verifier));
    }

    @Bean
    public WebClient  webClient(){

        WebClient webClient = new WebClient(BrowserVersion.CHROME);

        // 设置当前的AJAX控制器
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        // 设置CSS支持
        webClient.getOptions().setCssEnabled(false);
        // 设置JavaScript是否启用
        webClient.getOptions().setJavaScriptEnabled(true);
        // 设置ActiveX是否启用
        webClient.getOptions().setActiveXNative(false);
        // 设置访问错误时是否抛出异常
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(true);
        // 设置JS报错时是否抛出异常
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        return webClient;
    }

}
