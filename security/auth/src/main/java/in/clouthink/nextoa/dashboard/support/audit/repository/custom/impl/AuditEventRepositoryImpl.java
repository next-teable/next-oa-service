package in.clouthink.nextoa.dashboard.support.audit.repository.custom.impl;

import in.clouthink.nextoa.dashboard.support.audit.model.AuditEvent;
import in.clouthink.nextoa.dashboard.support.audit.model.AuditEventQueryParameter;
import in.clouthink.nextoa.dashboard.support.audit.repository.custom.AuditEventRepositoryCustom;
import in.clouthink.nextoa.repository.custom.impl.AbstractCustomRepositoryImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class AuditEventRepositoryImpl extends AbstractCustomRepositoryImpl implements
        AuditEventRepositoryCustom {
    
    @Override
    public Page<AuditEvent> queryPage(AuditEventQueryParameter queryRequest) {
        int start = queryRequest.getStart();
        int limit = queryRequest.getLimit();
        Query query = buildQuery(queryRequest);
        
        PageRequest pageable = new PageRequest(start,
                                               limit,
                                               new Sort(Sort.Direction.DESC,
                                                        "requestedAt"));
        query.with(pageable);
        long count = mongoTemplate.count(query, AuditEvent.class);
        List<AuditEvent> list = mongoTemplate.find(query, AuditEvent.class);
        
        return new PageImpl<>(list, pageable, count);
    }
    
    private Query buildQuery(AuditEventQueryParameter request) {
        Query query = new Query();
        Boolean error = request.getError();

        if (!StringUtils.isEmpty(request.getRequestedBy())) {
            query.addCriteria(Criteria.where("requestedBy").regex(request.getRequestedBy()));
        }
        
        if (request.getBeginDate() != null && request.getEndDate() != null) {
            Criteria criteria = new Criteria().andOperator(Criteria.where("requestedAt")
                                                                   .gte(request.getBeginDate()),
                                                           Criteria.where("requestedAt")
                                                                   .lte(request.getEndDate()));
            query.addCriteria(criteria);
        }
        else if (request.getBeginDate() != null) {
            query.addCriteria(Criteria.where("requestedAt")
                                      .gte(request.getBeginDate()));
        }
        else if (request.getEndDate() != null) {
            query.addCriteria(Criteria.where("requestedAt")
                                      .lte(request.getEndDate()));
        }
        
        if (error != null) {
            query.addCriteria(Criteria.where("error").is(error.booleanValue()));
        }
        return query;
    }
    
}
