package in.clouthink.nextoa.bl.repository.custom;

import in.clouthink.nextoa.bl.model.News;
import in.clouthink.nextoa.bl.request.NewsQueryRequest;
import org.springframework.data.domain.Page;

public interface NewsRepositoryCustom {

	Page<News> queryPage(NewsQueryRequest queryRequest);

	void updateReadCounter(String id, int readCounter);

}
