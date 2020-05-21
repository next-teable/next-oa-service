package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.BaseModel;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * 项目预算（ 超出阈值系统报警）
 *
 * @author dz
 */
@Document(collection = "Projects")
public class ProjectBudget extends BaseModel {

	@Indexed
	@DBRef(lazy = true)
	private Project project;

	@Indexed
	int year;

	//阈值,超出后要报警
	private BigDecimal threshold;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public BigDecimal getThreshold() {
		return threshold;
	}

	public void setThreshold(BigDecimal threshold) {
		this.threshold = threshold;
	}
}
