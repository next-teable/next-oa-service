package in.clouthink.nextoa.repository.custom;

import in.clouthink.nextoa.model.Paper;
import in.clouthink.nextoa.model.PaperAction;
import in.clouthink.nextoa.model.dtr.PaperActionQueryRequest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 *
 */
public interface PaperActionRepositoryCustom {

	Page<PaperAction> queryPage(Paper paper, PaperActionQueryRequest request);

	List<PaperAction> queryList(Paper paper, PaperActionQueryRequest request);

}
