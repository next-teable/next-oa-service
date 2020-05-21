package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.openapi.dto.AttachmentSummary;
import cn.com.starest.nextoa.project.domain.model.AbstractContract;
import cn.com.starest.nextoa.project.domain.model.Contract;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dz
 */
public class ContractDetail extends ContractSummary {

	public static void convert(Contract fromObj, ContractDetail result) {
		ContractSummary.convert((AbstractContract) fromObj, result);
		if (fromObj.getProjects() != null) {
			result.setProjects(fromObj.getProjects().stream().map(project -> {
				SimpleDto item = new SimpleDto();
				item.setId(project.getId());
				item.setName(project.getName());
				return item;
			}).collect(Collectors.toList()));
		}
	}

	public static ContractDetail from(Contract fromObj) {
		if (fromObj == null) {
			return null;
		}
		ContractDetail result = new ContractDetail();
		convert(fromObj, result);
		return result;
	}

	//项目名称（区域）*
	private List<SimpleDto> projects;

	private List<AttachmentSummary> attachments;

	public List<SimpleDto> getProjects() {
		return projects;
	}

	public void setProjects(List<SimpleDto> projects) {
		this.projects = projects;
	}

	public List<AttachmentSummary> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<AttachmentSummary> attachments) {
		this.attachments = attachments;
	}

}
