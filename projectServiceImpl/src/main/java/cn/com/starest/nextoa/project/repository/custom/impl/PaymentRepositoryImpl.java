package cn.com.starest.nextoa.project.repository.custom.impl;

import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.domain.request.PaymentQueryRequest;
import cn.com.starest.nextoa.project.repository.custom.PaymentRepositoryCustom;
import cn.com.starest.nextoa.shared.util.DateTimeUtils;
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
public class PaymentRepositoryImpl extends AbstractCustomRepositoryImpl implements PaymentRepositoryCustom {

    @Override
    public BigDecimal calculateTotalAmount(Company company, int year) {
        Aggregation aggregation = newAggregation(match(Criteria.where("company.$id")
                        .is(new ObjectId(company.getId()))
                        .andOperator(Criteria.where("payAt")
                                        .gte(DateTimeUtils.startOfYear(year)),
                                Criteria.where("payAt")
                                        .lte(DateTimeUtils.endOfYear(year)))
                        .and("bizDepartment")
                        .ne(null)),
                group("company").sum("amount").as("amount"),
                project("amount"));

        AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
                Payment.class,
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
    public BigDecimal calculateProjectTotalAmount(Company company, int year) {
        Aggregation aggregation = newAggregation(match(Criteria.where("company.$id")
                        .is(new ObjectId(company.getId()))
                        .andOperator(Criteria.where("payAt")
                                        .gte(DateTimeUtils.startOfYear(year)),
                                Criteria.where("payAt")
                                        .lte(DateTimeUtils.endOfYear(year)))
                        .and("project")
                        .ne(null)),
                group("company").sum("amount").as("amount"),
                project("amount"));

        AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
                Payment.class,
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
    public BigDecimal calculateProjectTotalAmount(Company company, int year, int month) {
        Aggregation aggregation = newAggregation(match(Criteria.where("company.$id")
                        .is(new ObjectId(company.getId()))
                        //															   .and("year")
                        //															   .is(year)
                        .and("project")
                        .ne(null)
                        .andOperator(Criteria.where("payAt")
                                        .gte(DateTimeUtils.startOfMonth(year,
                                                month)),
                                Criteria.where("payAt")
                                        .lte(DateTimeUtils.endOfMonth(year,
                                                month)))),
                group("company").sum("amount").as("amount"),
                project("amount"));

        AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
                Payment.class,
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
    public BigDecimal calculateBizDepartmentTotalAmount(Company company, int year, int month) {
        Aggregation aggregation = newAggregation(match(Criteria.where("company.$id")
                        .is(new ObjectId(company.getId()))
                        .and("bizDepartment")
                        .ne(null)
                        .andOperator(Criteria.where("payAt")
                                        .gte(DateTimeUtils.startOfMonth(year,
                                                month)),
                                Criteria.where("payAt")
                                        .lte(DateTimeUtils.endOfMonth(year,
                                                month)))),
                group("company").sum("amount").as("amount"),
                project("amount"));

        AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
                Payment.class,
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
    public BigDecimal calculateTotalAmount(BizDepartment bizDepartment, int year) {
        Aggregation aggregation = newAggregation(match(Criteria.where("bizDepartment.$id")
                        .is(new ObjectId(bizDepartment.getId()))
                        .and("year")
                        .is(year)
                //															   .andOperator(Criteria.where("payAt")
                //																					.gte(DateTimeUtils.startOfYear(year)),
                //																			Criteria.where("payAt")
                //																					.lte(DateTimeUtils.endOfYear(year)))
        ), group("bizDepartment").sum("amount").as("amount"), project("amount"));

        AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
                Payment.class,
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
    public BigDecimal calculateTotalAmount(Project project) {
        Aggregation aggregation = newAggregation(match(Criteria.where("project.$id").is(new ObjectId(project.getId()))),
                group("project").sum("amount").as("amount"),
                project("amount"));

        AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
                Payment.class,
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
        Aggregation aggregation = newAggregation(match(Criteria.where("project.$id")
                        .is(new ObjectId(project.getId()))
                        .andOperator(Criteria.where("payAt")
                                        .gte(DateTimeUtils.startOfYear(year)),
                                Criteria.where("payAt")
                                        .lte(DateTimeUtils.endOfYear(year)))),
                group("project").sum("amount").as("amount"),
                project("amount"));

        AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
                Payment.class,
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
    public BigDecimal calculateTotalAmount(ReceiveInvoice receiveInvoice) {
        Aggregation aggregation = newAggregation(match(Criteria.where("receiveInvoice.$id")
                        .is(new ObjectId(receiveInvoice.getId()))),
                group("receiveInvoice").sum("amount").as("amount"),
                project("amount"));

        AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
                Payment.class,
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
    public BigDecimal calculateTotalAmount(String bizRefId, String payToId) {
        Aggregation aggregation = newAggregation(match(Criteria.where("payTo.$id")
                        .is(new ObjectId(payToId))
                        .and("bizRefId")
                        .is(bizRefId)),
                group("bizRefId").sum("amount").as("amount"),
                project("amount"));

        AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
                Payment.class,
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
    public Page<Payment> queryPage(PaymentQueryRequest queryRequest) {
        int start = queryRequest.getStart();
        int limit = queryRequest.getLimit();
        Query query = buildQuery(queryRequest);

        PageRequest pageable = new PageRequest(start, limit, new Sort(Sort.Direction.DESC, "createdAt"));
        query.with(pageable);
        long count = mongoTemplate.count(query, Payment.class);
        List<Payment> list = mongoTemplate.find(query, Payment.class);

        return new PageImpl<>(list, pageable, count);
    }

    private Query buildQuery(PaymentQueryRequest request) {
        Query query = new Query();

        if (!StringUtils.isEmpty(request.getProjectId())) {
            query.addCriteria(Criteria.where("project.$id").is(new ObjectId(request.getProjectId())));
        }

        if (!StringUtils.isEmpty(request.getCompanyId())) {
            query.addCriteria(Criteria.where("company.$id").is(new ObjectId(request.getCompanyId())));
        }

        if (!StringUtils.isEmpty(request.getBizDepartmentId())) {
            query.addCriteria(Criteria.where("bizDepartment.$id").is(new ObjectId(request.getBizDepartmentId())));
        }

        if (!StringUtils.isEmpty(request.getSubContractorId())) {
            query.addCriteria(Criteria.where("subContractor.$id").is(new ObjectId(request.getSubContractorId())));
        }

        if (!StringUtils.isEmpty(request.getPayToId())) {
            query.addCriteria(Criteria.where("payTo.$id").is(new ObjectId(request.getPayToId())));
        }

        if (!StringUtils.isEmpty(request.getAccountSubjectId())) {
            query.addCriteria(Criteria.where("accountSubject.$id").is(new ObjectId(request.getAccountSubjectId())));
        }

        if (!StringUtils.isEmpty(request.getSubAccountSubjectId())) {
            query.addCriteria(Criteria.where("subAccountSubject.$id")
                    .is(new ObjectId(request.getSubAccountSubjectId())));
        }

        if (request.getSource() != null) {
            query.addCriteria(Criteria.where("source").is(request.getSource()));
        }

        if (request.getYear() != null) {
            query.addCriteria(Criteria.where("year").is(request.getYear()));
            //			Date startOfYear = DateTimeUtils.startOfYear(request.getYear());
            //			Date endOfYear = DateTimeUtils.endOfYear(request.getYear());
            //			query.addCriteria(new Criteria().andOperator(Criteria.where("payAt").gte(startOfYear),
            //														 Criteria.where("payAt").lte(endOfYear)));
        }

        if (!StringUtils.isEmpty(request.getOrderId())) {
            query.addCriteria(Criteria.where("order.$id")
                    .is(new ObjectId(request.getOrderId())));
        }

        return query;
    }

}
