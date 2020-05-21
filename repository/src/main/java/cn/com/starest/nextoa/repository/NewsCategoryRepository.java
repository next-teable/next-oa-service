package cn.com.starest.nextoa.repository;

import cn.com.starest.nextoa.model.NewsCategory;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 */
public interface NewsCategoryRepository extends AbstractRepository<NewsCategory> {

	Page<NewsCategory> findByCreatedBy(User createdBy, Pageable pageable);

}