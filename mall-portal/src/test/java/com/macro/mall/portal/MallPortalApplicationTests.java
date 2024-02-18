package com.macro.mall.portal;

import cn.hutool.json.JSONUtil;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.macro.mall.portal.domain.LogisticsTraceNode;
import com.macro.mall.portal.domain.WeChatPayParam;
import com.macro.mall.portal.service.WeChatPayService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.*;


@SpringBootTest
public class MallPortalApplicationTests {

    @Autowired
    private WeChatPayService weChatPayService;

    @Autowired
    private WebClient webClient;


    @Test
    public void testWeChatPay() throws Exception {
        WeChatPayParam weChatPayParam = new WeChatPayParam();
        String outTradeNo = UUID.randomUUID().toString().replaceAll("-", "");
        weChatPayParam.setOutTradeNo(outTradeNo);
        weChatPayParam.setSubject("Image形象店-深圳腾大-QQ公仔");
        weChatPayParam.setTotalAmount(1);
        System.out.println(weChatPayService.pay(weChatPayParam));
    }


    @Test
    public void testSearch() {
        int i = 0;

        try {
            HtmlPage htmlPage = webClient.getPage("https://page.cainiao.com/guoguo/app-myexpress-taobao/express-detail.html?mailNo=773269901906519");
            do {
                // 阻塞当前线程，直到指定时间后结束
                webClient.waitForBackgroundJavaScript(3 * 1000);
                DomNodeList<DomElement> elementsByTagNameSubmits = htmlPage.getElementsByTagName("ul");
                List<LogisticsTraceNode> nodeList = new ArrayList<>();
                for (DomElement element : elementsByTagNameSubmits) {
                    DomNodeList<HtmlElement> liElements = element.getElementsByTagName("li");
                    if (CollectionUtils.isEmpty(liElements)) {
                        i++;
                        continue;
                    }
                    i = 2;
                    for (HtmlElement liElement : liElements) {
                        List<HtmlElement> timeElements = liElement.getByXPath("./div[@class=\"feed-item_datetime\"]//div");
                        List<HtmlElement> contentElements = liElement.getByXPath("./div[@class=\"feed-item_content\"]");
                        if (timeElements != null && timeElements.size() >= 2
                                && contentElements != null && contentElements.size() >= 1) {
                            String hourElement = timeElements.get(0).asNormalizedText();
                            String dateElement = timeElements.get(1).getTextContent();
                            String contentElement = contentElements.get(0).getTextContent();
                            nodeList.add(new LogisticsTraceNode(dateElement + " " + hourElement + ":00", contentElement));
                        }
                    }
                }
                System.out.println(JSONUtil.toJsonStr(nodeList));
            } while (i <= 1);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        System.out.println("APIV3密钥:" + UUID.randomUUID().toString().replaceAll("-", ""));
    }
}
