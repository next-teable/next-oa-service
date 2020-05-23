package in.clouthink.nextoa.model.dtr;

import in.clouthink.nextoa.model.PaperType;

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
