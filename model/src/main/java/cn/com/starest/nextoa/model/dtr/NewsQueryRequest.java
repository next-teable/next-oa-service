package cn.com.starest.nextoa.model.dtr;

import cn.com.starest.nextoa.model.News;

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
