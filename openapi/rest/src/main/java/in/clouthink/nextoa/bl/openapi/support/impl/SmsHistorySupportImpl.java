package in.clouthink.nextoa.bl.openapi.support.impl;

import in.clouthink.nextoa.bl.model.SmsHistory;
import in.clouthink.nextoa.bl.openapi.dto.SmsHistorySummary;
import in.clouthink.nextoa.bl.openapi.support.SmsHistorySupport;
import in.clouthink.nextoa.bl.request.SmsHistoryQueryRequest;
import in.clouthink.nextoa.bl.service.SmsHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SmsHistorySupportImpl implements SmsHistorySupport {
    
    @Autowired
    private SmsHistoryService smsHistoryService;
    
    @Override
    public Page<SmsHistorySummary> findPage(SmsHistoryQueryRequest request) {
        Page<SmsHistory> smsHistories = smsHistoryService.findPage(request);
        return new PageImpl<>(smsHistories.getContent()
                                          .stream()
                                          .map(SmsHistorySummary::from)
                                          .collect(Collectors.toList()),
                              new PageRequest(request.getStart(),
                                              request.getLimit()),
                              smsHistories.getTotalElements());
    }
}
