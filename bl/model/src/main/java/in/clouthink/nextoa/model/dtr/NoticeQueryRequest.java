package in.clouthink.nextoa.model.dtr;

import java.util.Date;

/**
 *
 */
public interface NoticeQueryRequest extends DateRangedQueryRequest {
    
    String getTitle();
    
    String getCategory();
    
    Boolean getPublished();

    Date getCreatedAtBegin();

    Date getCreatedAtEnd();

}
