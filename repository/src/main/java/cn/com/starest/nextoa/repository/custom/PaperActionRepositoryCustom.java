package cn.com.starest.nextoa.repository.custom;

import cn.com.starest.nextoa.model.Paper;
import cn.com.starest.nextoa.model.PaperAction;
import cn.com.starest.nextoa.model.dtr.PaperActionQueryRequest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 *
 */
public interface PaperActionRepositoryCustom {

	Page<PaperAction> queryPage(Paper paper, PaperActionQueryRequest request);

	List<PaperAction> queryList(Paper paper, PaperActionQueryRequest request);

}
