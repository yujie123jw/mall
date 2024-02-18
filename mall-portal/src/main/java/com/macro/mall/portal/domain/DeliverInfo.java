package com.macro.mall.portal.domain;

import lombok.Data;

/**
 * 快递信息
 *
 * @author:YuJie
 * @create: 2024-02-14 11:34
 * @Description:
 */
@Data
public class DeliverInfo {

    /**
     * 快递号
     */
    private String mailNo;

    private String orderCode = "";
    private String cpCode = "";
    /**
     * APPName
     */
    private String appName = "GUOGUO";
    /**
     * 操作员
     */
    private String actor = "RECEIVER";
    private Boolean isAccoutOut = true;
    private Boolean isShowConsignDetail = true;
    private Boolean ignoreInvalidNode = true;
    private Boolean isUnique = true;
    private Boolean isStandard = true;
    private Boolean isShowItem = true;
    private Boolean isShowTemporalityService = true;
    private Boolean isShowCommonService = true;
    private Boolean isStandardActionCode = true;
    private Boolean isOrderByAction = true;
    private Boolean isShowExpressMan = true;
    private Boolean isShowProgressbar = true;
    private Boolean isShowLastOneService = true;
    private Boolean isShowServiceProvider = true;
    private Boolean isShowDeliveryProgress = true;
}
