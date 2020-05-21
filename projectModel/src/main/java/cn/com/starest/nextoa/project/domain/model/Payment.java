package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.User;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 付款
 *
 * @author dz
 */
@Document(collection = "Payments")
public class Payment extends AbstractPayment {

	//generate by system
	@Indexed
	private String code;

	//付款日期
	@Indexed
	private Date payAt;

	//操作人
	@DBRef(lazy = true)
	private User handleBy;

	//数据来源
	@Indexed
	private PaymentSource source;

	//***********************************
	// 当数据源为REIMBURSE的时候,以下字段有值
	//***********************************

	//数据源 id
	@Indexed
	private String sourceId;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getPayAt() {
		return payAt;
	}

	public void setPayAt(Date payAt) {
		this.payAt = payAt;
	}

	public User getHandleBy() {
		return handleBy;
	}

	public void setHandleBy(User handleBy) {
		this.handleBy = handleBy;
	}

	public PaymentSource getSource() {
		return source;
	}

	public void setSource(PaymentSource source) {
		this.source = source;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
}
