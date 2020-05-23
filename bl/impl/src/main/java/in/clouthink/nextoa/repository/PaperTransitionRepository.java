package in.clouthink.nextoa.repository;

import in.clouthink.nextoa.model.Paper;
import in.clouthink.nextoa.model.PaperTransition;
import in.clouthink.nextoa.repository.shared.AbstractRepository;

import java.util.List;


/**
 *
 */
public interface PaperTransitionRepository extends AbstractRepository<PaperTransition> {

	List<PaperTransition> findListByPaperOrderByCreatedAtDesc(Paper paper);

}
