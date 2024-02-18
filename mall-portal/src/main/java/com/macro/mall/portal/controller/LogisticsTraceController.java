package com.macro.mall.portal.controller;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.portal.service.LogisticsTraceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:YuJie
 * @create: 2024-02-17 16:36
 * @Description:
 */
@RestController
@Api(tags = "LogisticsTraceController")
@Tag(name = "LogisticsTraceController", description = "物流信息")
@RequestMapping("/logistics")
public class LogisticsTraceController {


    @Autowired
    private LogisticsTraceService logisitcsTraceService;



    @ApiOperation("查询物流信息")
    @GetMapping("/getTrace")
    public CommonResult getLogistics(String mailNo) {
        //根据订单号查询物流信息
        return CommonResult.success(logisitcsTraceService.getLogistics(mailNo));
    }


}
