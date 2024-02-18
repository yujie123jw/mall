package com.macro.mall.portal.controller;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.portal.domain.WeChatPayParam;
import com.macro.mall.portal.service.WeChatPayService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:YuJie
 * @create: 2024-02-14 21:35
 * @Description:
 */
@RestController
@RequestMapping("/wechatpay")
public class WeChatPayController {

    @Autowired
    private WeChatPayService weChatPaymentService;

    @GetMapping("/pay")
    @ApiOperation(value = "微信支付获取链接",notes = "微信支付链接")
    public CommonResult getPaymentURL(WeChatPayParam weChatPayParam) {
        return CommonResult.success(weChatPaymentService.pay(weChatPayParam));
    }

    @ApiOperation(value = "支付宝异步回调",notes = "必须为POST请求，执行成功返回success，执行失败返回failure")
    @RequestMapping(value = "/notify", method = RequestMethod.POST)
    public String notify(HttpServletRequest request){
        return  null;
    }


    @ApiOperation(value = "微信统一收单线下交易查询",notes = "订单支付成功返回交易状态：TRADE_SUCCESS")
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<String> query(String outTradeNo, String tradeNo){
     return null;
    }




}
