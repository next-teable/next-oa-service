package in.clouthink.nextoa.bl.repository.custom.impl;

import in.clouthink.nextoa.bl.model.Paper;
import in.clouthink.nextoa.bl.model.PaperAction;
import in.clouthink.nextoa.bl.repository.custom.PaperActionRepositoryCustom;
import in.clouthink.nextoa.bl.request.PaperActionQueryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class PaperActionRepositoryImpl extends AbstractCustomRepositoryImpl implements PaperActionRepositoryCustom {

	@Override
	public Page<PaperAction> queryPage(Paper paper, PaperActionQueryRequest request) {
		Query query = buildQuery(request);
		query.addCriteria(Criteria.where("paper").is(paper));

		int start = request.getStart();
		int limit = request.getLimit();
		PageRequest pageable = new PageRequest(start, limit, new Sort(Sort.Direction.DESC, "createdAt"));
		query.with(pageable);
		long count = mongoTemplate.count(query, PaperAction.class);
		List<PaperAction> list = mongoTemplate.find(query, PaperAction.class);

		return new PageImpl<>(list, pageable, count);
	}

	@Override
	public List<PaperAction> queryList(Paper paper, PaperActionQueryRequest request) {
		Query query = buildQuery(request);
		query.addCriteria(Criteria.where("paper").is(paper)).with(new Sort(Sort.Direction.DESC, "createdAt"));
		return mongoTemplate.find(query, PaperAction.class);
	}

	private Query buildQuery(PaperActionQueryRequest request) {
		Query query = new Query();

		if (request.getPaperActionTypes() != null && request.getPaperActionTypes().length > 0) {
			query.addCriteria(Criteria.where("type").in(Arrays.asList(request.getPaperActionTypes())));
		}

		return query;
	}

}
