package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.model.ContractHistory;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dz
 */
public class ContractHistorySummary extends ContractSummary {

	public static void convert(ContractHistory fromObj, ContractHistorySummary result) {
		ContractSummary.convert(fromObj, result);
		if (fromObj.getProjects() != null) {
			result.setProjects(fromObj.getProjects().stream().map(project -> {
				SimpleDto item = new SimpleDto();
				item.setId(project.getId());
				item.setName(project.getName());
				return item;
			}).collect(Collectors.toList()));
		}
		if (fromObj.getModifiedBy() != null) {
			result.setModifiedById(fromObj.getModifiedBy().getId());
			result.setModifiedByName(fromObj.getModifiedBy().getUsername());
		}
	}

	public static ContractHistorySummary from(ContractHistory fromObj) {
		if (fromObj == null) {
			return null;
		}
		ContractHistorySummary result = new ContractHistorySummary();
		convert(fromObj, result);
		return result;
	}

	//项目名称（区域）*
	private List<SimpleDto> projects;

	private Date modifiedAt;

	private String modifiedById;

	private String modifiedByName;

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public String getModifiedById() {
		return modifiedById;
	}

	public void setModifiedById(String modifiedById) {
		this.modifiedById = modifiedById;
	}

	public String getModifiedByName() {
		return modifiedByName;
	}

	public void setModifiedByName(String modifiedByName) {
		this.modifiedByName = modifiedByName;
	}

	public List<SimpleDto> getProjects() {
		return projects;
	}

	public void setProjects(List<SimpleDto> projects) {
		this.projects = projects;
	}

}
