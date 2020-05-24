package in.clouthink.nextoa.security.audit.repository;


import in.clouthink.nextoa.security.audit.model.AggregationType;
import in.clouthink.nextoa.security.audit.model.AuditEventAggregation;
import in.clouthink.nextoa.shared.repository.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuditEventAggregationRepository extends AbstractRepository<AuditEventAggregation> {

	AuditEventAggregation findByAggregationTypeAndAggregationKey(AggregationType type, String key);

	List<AuditEventAggregation> findByAggregationTypeAndAggregationKeyLike(AggregationType type, String key);

	Page<AuditEventAggregation> findPageByAggregationTypeOrderByAggregationKeyDesc(AggregationType type,
																				   Pageable pageable);

}
