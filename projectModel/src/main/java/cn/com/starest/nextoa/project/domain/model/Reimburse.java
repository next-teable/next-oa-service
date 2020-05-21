package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.Paper;
import cn.com.starest.nextoa.model.User;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 报销（和快文一起）
 *
 * @author dz
 */
@Document(collection = "Reimburses")
public class Reimburse extends AbstractPayment {

	@Indexed
	@DBRef(lazy = true)
	private Paper paper;

	//报销发起人* （例如分包单位谁请的款）
	//默认当前用户，可以修改
	@Indexed
	@DBRef(lazy = true)
	private User applyBy;

	//是否结算
	private boolean settled;

	private Date settleAt;

	@Indexed
	@DBRef(lazy = true)
	private User settleBy;

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public User getApplyBy() {
		return applyBy;
	}

	public void setApplyBy(User applyBy) {
		this.applyBy = applyBy;
	}

	public boolean isSettled() {
		return settled;
	}

	public void setSettled(boolean settled) {
		this.settled = settled;
	}

	public Date getSettleAt() {
		return settleAt;
	}

	public void setSettleAt(Date settleAt) {
		this.settleAt = settleAt;
	}

	public User getSettleBy() {
		return settleBy;
	}

	public void setSettleBy(User settleBy) {
		this.settleBy = settleBy;
	}

}
