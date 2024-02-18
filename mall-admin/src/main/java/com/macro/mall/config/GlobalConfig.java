package com.macro.mall.config;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author:YuJie
 * @create: 2024-02-19 00:07
 * @Description:
 */
@Configuration
public class GlobalConfig {

    @Bean
    public WebClient webClient(){

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
        // 阻塞当前线程，直到指定时间后结束
        webClient.waitForBackgroundJavaScript(3 * 1000L);
        return webClient;
    }
}
