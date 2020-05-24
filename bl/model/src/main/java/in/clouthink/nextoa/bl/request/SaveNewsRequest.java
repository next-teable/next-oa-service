package in.clouthink.nextoa.bl.request;

import in.clouthink.nextoa.bl.model.News;

/**
 *
 */
public interface SaveNewsRequest {
    
    String getCategory();
    
    String getTitle();
    
    // 不超过140个字
    String getSummary();
    
    String getContent();
    
    News.NewsType getNewsType();
    
}
