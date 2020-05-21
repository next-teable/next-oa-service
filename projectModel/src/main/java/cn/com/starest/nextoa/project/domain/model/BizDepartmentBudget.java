package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.BaseModel;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * 部门预算（ 超出阈值系统报警）
 *
 * @author dz
 */
@Document(collection = "BizDepartmentBudgets")
public class BizDepartmentBudget extends BaseModel {

	@Indexed
	@DBRef(lazy = true)
	private BizDepartment bizDepartment;

	@Indexed
	int year;

	//预算阈值,超出后要报警
	private BigDecimal budget;

	public BizDepartment getBizDepartment() {
		return bizDepartment;
	}

	public void setBizDepartment(BizDepartment bizDepartment) {
		this.bizDepartment = bizDepartment;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}
}
