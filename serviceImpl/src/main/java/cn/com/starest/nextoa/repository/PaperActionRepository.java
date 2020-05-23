package cn.com.starest.nextoa.repository;

import cn.com.starest.nextoa.model.Paper;
import cn.com.starest.nextoa.model.PaperAction;
import cn.com.starest.nextoa.model.PaperActionType;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.repository.custom.PaperActionRepositoryCustom;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;
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