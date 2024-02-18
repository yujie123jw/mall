package com.macro.mall.portal.service.impl;

import cn.hutool.json.JSONUtil;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.macro.mall.portal.domain.LogisticsTraceNode;
import com.macro.mall.portal.service.LogisticsTraceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:YuJie
 * @create: 2024-02-18 14:57
 * @Description:
 */
@Slf4j
@Service
public class LogisticsTraceServiceImpl implements LogisticsTraceService {

    @Value("${logistic.trace.mailUrl:\"https://page.cainiao.com/guoguo/app-myexpress-taobao/express-detail.html?mailNo=\"}")
    private String mailUrlPreffix;


    @Autowired
    private WebClient webClient;

    @Override
    public List<LogisticsTraceNode> getLogistics(String mailNo) {
        List<LogisticsTraceNode> nodeList = new ArrayList<>();
        try {
            int i = 0;
            HtmlPage htmlPage = webClient.getPage(mailUrlPreffix +mailNo);
            do {
                // 阻塞当前线程，直到指定时间后结束
                webClient.waitForBackgroundJavaScript(3 * 1000L);
                DomNodeList<DomElement> elementsByTagNameSubmits = htmlPage.getElementsByTagName("ul");
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
                                && contentElements != null && contentElements.isEmpty()) {
                            String hourElement = timeElements.get(0).asNormalizedText();
                            String dateElement = timeElements.get(1).getTextContent();
                            String contentElement = contentElements.get(0).getTextContent();
                            nodeList.add(new LogisticsTraceNode(dateElement + " " + hourElement + ":00", contentElement));
                        }
                    }
                }
                i++;
                log.info("【邮寄信息】：{}---{}",mailNo,JSONUtil.toJsonStr(nodeList));
            } while (i <= 1);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return nodeList;
    }
}
