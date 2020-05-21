package cn.com.starest.nextoa.project.repository.custom.impl;

import cn.com.starest.nextoa.project.domain.model.IssueInvoice;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.domain.request.IssueInvoiceQueryRequest;
import cn.com.starest.nextoa.project.repository.custom.IssueInvoiceRepositoryCustom;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class IssueInvoiceRepositoryImpl extends AbstractCustomRepositoryImpl implements IssueInvoiceRepositoryCustom {

    @Override
    public BigDecimal calculateTotalAmount(Project project) {
        Aggregation aggregation = newAggregation(match(Criteria.where("project.$id").is(new ObjectId(project.getId()))),
                group("project").sum("amount").as("amount"),
                project("amount"));

        AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
                IssueInvoice.class,
                AggregationAmount.class);
        if (aggregationResults != null) {
            AggregationAmount result = aggregationResults.getUniqueMappedResult();
            if (result != null) {
                return result.getAmount();
            }
        }
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal calculateTotalAmount(Project project, int year) {
        Aggregation aggregation = newAggregation(match(Criteria.where("project.$id").is(new ObjectId(project.getId()))
                        .and("orderYear").is(year)),
                group("project").sum("amount").as("amount"),
                project("amount"));

        AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
                IssueInvoice.class,
                AggregationAmount.class);
        if (aggregationResults != null) {
            AggregationAmount result = aggregationResults.getUniqueMappedResult();
            if (result != null) {
                return result.getAmount();
            }
        }
        return BigDecimal.ZERO;
    }

    @Override
    public Page<IssueInvoice> queryPage(IssueInvoiceQueryRequest queryRequest) {
        int start = queryRequest.getStart();
        int limit = queryRequest.getLimit();
        Query query = buildQuery(queryRequest);

        PageRequest pageable = new PageRequest(start, limit, new Sort(Sort.Direction.DESC, "createdAt"));
        query.with(pageable);
        long count = mongoTemplate.count(query, IssueInvoice.class);
        List<IssueInvoice> list = mongoTemplate.find(query, IssueInvoice.class);

        return new PageImpl<>(list, pageable, count);
    }

    private Query buildQuery(IssueInvoiceQueryRequest request) {
        Query query = new Query();
        if (!StringUtils.isEmpty(request.getProjectId())) {
            query.addCriteria(Criteria.where("project.$id").is(new ObjectId(request.getProjectId())));
        }
        if (!StringUtils.isEmpty(request.getContractId())) {
            query.addCriteria(Criteria.where("contract.$id").is(new ObjectId(request.getContractId())));
        }
        if (!StringUtils.isEmpty(request.getOrderId())) {
            query.addCriteria(Criteria.where("order.$id").is(new ObjectId(request.getOrderId())));
        }

        if (!StringUtils.isEmpty(request.getFirstPartyId())) {
            query.addCriteria(Criteria.where("firstParty.$id").is(new ObjectId(request.getFirstPartyId())));
        }

        if (request.getMinAmount() != null && request.getMaxAmount() != null) {
            Criteria criteria = new Criteria().andOperator(Criteria.where("amount").gte(request.getMinAmount()),
                    Criteria.where("amount").lte(request.getMaxAmount()));
            query.addCriteria(criteria);
        } else if (request.getMinAmount() != null) {
            query.addCriteria(Criteria.where("amount").gte(request.getMinAmount()));
        } else if (request.getMaxAmount() != null) {
            query.addCriteria(Criteria.where("amount").lte(request.getMaxAmount()));
        }

        return query;
    }

}
