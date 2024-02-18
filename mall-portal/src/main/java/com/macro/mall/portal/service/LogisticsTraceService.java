package com.macro.mall.portal.service;

import com.macro.mall.portal.domain.LogisticsTraceNode;

import java.util.List;

public interface LogisticsTraceService {

    /**
     * 获取物流信息
     * @param mailNo
     * @return
     */
    List<LogisticsTraceNode> getLogistics(String mailNo);

}
