package cn.com.starest.nextoa.project.repository.custom;

import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.domain.model.ReceiveInvoice;
import cn.com.starest.nextoa.project.domain.request.ReceiveInvoiceQueryRequest;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

/**
 * @author dz
 */
public interface ReceiveInvoiceRepositoryCustom {

	BigDecimal calculateTotalAmount(Project project);

	Page<ReceiveInvoice> queryPage(ReceiveInvoiceQueryRequest request);

}
