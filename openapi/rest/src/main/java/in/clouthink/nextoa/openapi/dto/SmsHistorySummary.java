package in.clouthink.nextoa.openapi.dto;

import in.clouthink.nextoa.model.SmsHistory;
import org.springframework.beans.BeanUtils;

import java.util.Date;

public class SmsHistorySummary {
    public static SmsHistorySummary from(SmsHistory smsHistory) {
        if (smsHistory == null) {
            return null;
        }
        SmsHistorySummary result = new SmsHistorySummary();
        BeanUtils.copyProperties(smsHistory, result);
        return result;
    }
    
    private String id;
    
    private String cellphone;
    
    private String message;
    
    private Date createdAt;
    
    private SmsHistory.SmsCategory category;
    
    private SmsHistory.SmsStatus status;
    
    private String failureReason;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getCellphone() {
        return cellphone;
    }
    
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public Date getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    public SmsHistory.SmsCategory getCategory() {
        return category;
    }
    
    public void setCategory(SmsHistory.SmsCategory category) {
        this.category = category;
    }
    
    public SmsHistory.SmsStatus getStatus() {
        return status;
    }
    
    public void setStatus(SmsHistory.SmsStatus status) {
        this.status = status;
    }
    
    public String getFailureReason() {
        return failureReason;
    }
    
    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }
}
