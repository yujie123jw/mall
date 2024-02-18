package com.macro.mall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author:YuJie
 * @create: 2024-02-17 00:43
 * @Description:
 */
@Data
@AllArgsConstructor
public class LogisticsTraceNode {

    /**
     * 时间
     */
    private String time;
    /**
     * 事件
     */
    private String content;
}
