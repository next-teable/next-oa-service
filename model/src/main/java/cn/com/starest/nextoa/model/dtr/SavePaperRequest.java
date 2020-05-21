package cn.com.starest.nextoa.model.dtr;

import cn.com.starest.nextoa.model.PaperType;

/**
 *
 */
public interface SavePaperRequest {
    
    String getCategory();
    
    String getTitle();
    
    String getContent();
    
    PaperType getType();
    
    Boolean getUrgent();
    
}
