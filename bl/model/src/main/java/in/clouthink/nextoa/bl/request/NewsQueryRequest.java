package in.clouthink.nextoa.bl.request;

import in.clouthink.nextoa.bl.model.News;
import in.clouthink.nextoa.shared.domain.request.DateRangedQueryRequest;

import java.util.Date;

/**
 *
 */
public interface NewsQueryRequest extends DateRangedQueryRequest {
    
    String getTitle();
    
    String getCategory();
    
    Boolean getPublished();
    
    Date getCreatedAtBegin();
    
    Date getCreatedAtEnd();
    
    News.NewsType getNewsType();
    
}
