package in.clouthink.nextoa.bl.request;

import in.clouthink.nextoa.shared.domain.request.DateRangedQueryRequest;

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
