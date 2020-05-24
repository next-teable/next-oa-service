package in.clouthink.nextoa.bl.repository.custom;

import in.clouthink.nextoa.bl.model.Paper;
import in.clouthink.nextoa.bl.model.PaperAction;
import in.clouthink.nextoa.bl.request.PaperActionQueryRequest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 *
 */
public interface PaperActionRepositoryCustom {

	Page<PaperAction> queryPage(Paper paper, PaperActionQueryRequest request);

	List<PaperAction> queryList(Paper paper, PaperActionQueryRequest request);

}
