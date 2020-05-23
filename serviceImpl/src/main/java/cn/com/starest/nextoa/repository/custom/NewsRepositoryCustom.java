package cn.com.starest.nextoa.repository.custom;

import cn.com.starest.nextoa.model.News;
import cn.com.starest.nextoa.model.dtr.NewsQueryRequest;
import org.springframework.data.domain.Page;

public interface NewsRepositoryCustom {

	Page<News> queryPage(NewsQueryRequest queryRequest);

	void updateReadCounter(String id, int readCounter);

}
