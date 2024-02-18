package com.macro.mall.service;

import com.macro.mall.dto.LogisticsTraceNode;

import java.util.List;

public interface LogisticsTraceService {

    /**
     * 获取物流信息
     * @param mailNo
     * @return
     */
    List<LogisticsTraceNode> getLogistics(String mailNo);

}
