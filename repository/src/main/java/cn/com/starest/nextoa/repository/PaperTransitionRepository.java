package cn.com.starest.nextoa.repository;

import cn.com.starest.nextoa.model.Paper;
import cn.com.starest.nextoa.model.PaperTransition;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

import java.util.List;


/**
 *
 */
public interface PaperTransitionRepository extends AbstractRepository<PaperTransition> {

	List<PaperTransition> findListByPaperOrderByCreatedAtDesc(Paper paper);

}