package in.clouthink.nextoa.bl.openapi.dto;

import in.clouthink.nextoa.bl.model.SmsHistory;
import in.clouthink.nextoa.bl.request.SmsHistoryQueryRequest;
import in.clouthink.nextoa.shared.domain.params.DateRangedQueryParameter;

public class SmsHistoriesQueryParameter extends DateRangedQueryParameter implements
        SmsHistoryQueryRequest {
    
    private String cellphone;
    
    private SmsHistory.SmsCategory category;
    
    private SmsHistory.SmsStatus status;
    
    @Override
    public String getCellphone() {
        return cellphone;
    }
    
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }
    
    @Override
    public SmsHistory.SmsCategory getCategory() {
        return category;
    }
    
    public void setCategory(SmsHistory.SmsCategory category) {
        this.category = category;
    }
    
    @Override
    public SmsHistory.SmsStatus getStatus() {
        return status;
    }
    
    public void setStatus(SmsHistory.SmsStatus status) {
        this.status = status;
    }
}
