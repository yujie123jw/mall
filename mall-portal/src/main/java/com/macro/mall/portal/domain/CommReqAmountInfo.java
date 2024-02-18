package com.macro.mall.portal.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author:YuJie
 * @create: 2024-02-14 23:08
 * @Description:
 */
@Data
@AllArgsConstructor
public class CommReqAmountInfo {


    /**
     * 总金额
     */
    private Integer total;

    /**
     * 货币CNY
     */
    private String currency;
}
