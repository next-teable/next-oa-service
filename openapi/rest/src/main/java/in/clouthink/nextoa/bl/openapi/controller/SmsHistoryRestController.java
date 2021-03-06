package in.clouthink.nextoa.bl.openapi.controller;

import in.clouthink.nextoa.bl.openapi.dto.SmsHistoriesQueryParameter;
import in.clouthink.nextoa.bl.openapi.dto.SmsHistorySummary;
import in.clouthink.nextoa.bl.openapi.support.SmsHistorySupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
