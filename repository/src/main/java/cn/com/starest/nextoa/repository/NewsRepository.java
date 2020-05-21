package cn.com.starest.nextoa.repository;

import cn.com.starest.nextoa.model.News;
import cn.com.starest.nextoa.repository.custom.NewsRepositoryCustom;
import cn.com.starest.nextoa.repository.custom.PaperRepositoryCustom;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 */
public interface NewsRepository extends AbstractRepository<News>, NewsRepositoryCustom {

	Page<News> findByPublished(boolean published, Pageable pageable);

}