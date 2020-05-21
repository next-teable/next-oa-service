package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.request.SavePaymentRequest;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author dz
 */
public class SavePaymentParameter extends AbstractSavePaymentParameter implements SavePaymentRequest {

	@NotNull(message = "付款时间不能为空")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date payAt;

	@Override
	public Date getPayAt() {
		return payAt;
	}

	public void setPayAt(Date payAt) {
		this.payAt = payAt;
	}

}
