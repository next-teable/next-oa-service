package in.clouthink.nextoa.bl.repository;

import in.clouthink.nextoa.bl.model.Paper;
import in.clouthink.nextoa.bl.model.PaperTransition;
import in.clouthink.nextoa.shared.repository.AbstractRepository;

import java.util.List;


/**
 *
 */
public interface PaperTransitionRepository extends AbstractRepository<PaperTransition> {

	List<PaperTransition> findListByPaperOrderByCreatedAtDesc(Paper paper);

}
