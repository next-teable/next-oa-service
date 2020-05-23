package in.clouthink.nextoa.repository.custom;

import in.clouthink.nextoa.model.News;
import in.clouthink.nextoa.model.dtr.NewsQueryRequest;
import org.springframework.data.domain.Page;

public interface NewsRepositoryCustom {

	Page<News> queryPage(NewsQueryRequest queryRequest);

	void updateReadCounter(String id, int readCounter);

}
