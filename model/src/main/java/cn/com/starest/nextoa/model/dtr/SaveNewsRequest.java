package cn.com.starest.nextoa.model.dtr;

import cn.com.starest.nextoa.model.News;

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
