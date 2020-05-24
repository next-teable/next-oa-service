package in.clouthink.nextoa.bl.request;

import in.clouthink.nextoa.bl.model.PaperType;

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
