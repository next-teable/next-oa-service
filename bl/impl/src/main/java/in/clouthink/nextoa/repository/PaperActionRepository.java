package in.clouthink.nextoa.repository;

import in.clouthink.nextoa.model.Paper;
import in.clouthink.nextoa.model.PaperAction;
import in.clouthink.nextoa.model.PaperActionType;
import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.repository.custom.PaperActionRepositoryCustom;
import in.clouthink.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 *
 */
public interface PaperActionRepository extends AbstractRepository<PaperAction>, PaperActionRepositoryCustom {

	List<PaperAction> findListByPaper(Paper paper);

	List<PaperAction> findListByIdInOrderByCreatedAtDesc(String[] ids);

	Page<PaperAction> findPageByPaper(Paper paper, Pageable pageable);

	PaperAction findFirstByPaperAndTypeAndCreatedBy(Paper paper, PaperActionType type, User byWho);

	int countByPaperAndType(Paper paper, PaperActionType type);

}
