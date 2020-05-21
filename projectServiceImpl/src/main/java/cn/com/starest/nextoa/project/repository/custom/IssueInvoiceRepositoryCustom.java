package cn.com.starest.nextoa.project.repository.custom;

import cn.com.starest.nextoa.project.domain.model.IssueInvoice;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.domain.request.IssueInvoiceQueryRequest;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

/**
 * @author dz
 */
public interface IssueInvoiceRepositoryCustom {

    /**
     * @param project
     * @return
     */
    BigDecimal calculateTotalAmount(Project project);

    BigDecimal calculateTotalAmount(Project project, int year);

    Page<IssueInvoice> queryPage(IssueInvoiceQueryRequest request);

}
