package cn.com.starest.nextoa.repository;

import cn.com.starest.nextoa.model.Paper;
import cn.com.starest.nextoa.repository.custom.PaperRepositoryCustom;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

/**
 *
 */
public interface PaperRepository extends AbstractRepository<Paper>, PaperRepositoryCustom {

}