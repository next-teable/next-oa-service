package in.clouthink.nextoa.model.dtr;

import in.clouthink.nextoa.model.News;

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
