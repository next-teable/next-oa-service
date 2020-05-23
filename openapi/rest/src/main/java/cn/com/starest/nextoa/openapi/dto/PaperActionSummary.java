package cn.com.starest.nextoa.openapi.dto;

import cn.com.starest.nextoa.model.PaperAction;
import cn.com.starest.nextoa.model.PaperActionType;

import java.util.Date;

public abstract class PaperActionSummary {
    
    static void convert(PaperAction action, PaperActionSummary result) {
        result.setId(action.getId());
        result.setType(action.getType());
        result.setCreatedAt(action.getCreatedAt());
        result.setHandlerId(action.getCreatedBy().getId());
        result.setHandlerName(action.getCreatedBy().getUsername());
        result.setHandlerAvatarId(action.getCreatedBy().getAvatarId());
    }
    
    private String id;
    
    private Date createdAt;

    private PaperActionType type;
    
    private String handlerId;
    
    private String handlerName;
    
    private String handlerAvatarId;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Date getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public PaperActionType getType() {
        return type;
    }

    public void setType(PaperActionType type) {
        this.type = type;
    }

    public String getHandlerId() {
        return handlerId;
    }
    
    public void setHandlerId(String handlerId) {
        this.handlerId = handlerId;
    }
    
    public String getHandlerName() {
        return handlerName;
    }
    
    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }
    
    public String getHandlerAvatarId() {
        return handlerAvatarId;
    }
    
    public void setHandlerAvatarId(String handlerAvatarId) {
        this.handlerAvatarId = handlerAvatarId;
    }
}
