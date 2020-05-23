package cn.com.starest.nextoa.openapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.starest.nextoa.openapi.dto.SmsHistoriesQueryParameter;
import cn.com.starest.nextoa.openapi.dto.SmsHistorySummary;
import cn.com.starest.nextoa.openapi.support.SmsHistorySupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("短信发送记录")
@RestController
@RequestMapping("/api/smsHistories")
public class SmsHistoryRestController {
    
    @Autowired
    private SmsHistorySupport smsHistorySupport;
    
    @ApiOperation(value = "获取短信发送记录（分页）")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Page<SmsHistorySummary> findPage(SmsHistoriesQueryParameter parameter) {
        return smsHistorySupport.findPage(parameter);
    }
    
}
