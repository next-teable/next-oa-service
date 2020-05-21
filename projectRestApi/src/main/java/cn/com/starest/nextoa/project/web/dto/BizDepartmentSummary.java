package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.model.shared.StringIdentifier;
import cn.com.starest.nextoa.openapi.dto.SimpleUser;
import cn.com.starest.nextoa.project.domain.model.BizDepartment;
import cn.com.starest.nextoa.project.domain.model.BizDepartmentType;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dz
 */
public class BizDepartmentSummary extends StringIdentifier {

	public static void convert(BizDepartment fromObj, BizDepartmentSummary result) {
		BeanUtils.copyProperties(fromObj, result, "managers");
		if (fromObj.getManagers() != null) {
			result.setManagers(fromObj.getManagers()
									  .stream()
									  .map(mgr -> SimpleUser.from(mgr))
									  .collect(Collectors.toList()));
		}
	}

	public static BizDepartmentSummary from(BizDepartment fromObj) {
		if (fromObj == null) {
			return null;
		}
		BizDepartmentSummary result = new BizDepartmentSummary();
		convert(fromObj, result);
		return result;
	}

	private String name;

	private String description;

	private String sort;

	private BizDepartmentType type;

	private List<SimpleUser> managers = new ArrayList();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public List<SimpleUser> getManagers() {
		return managers;
	}

	public void setManagers(List<SimpleUser> managers) {
		this.managers = managers;
	}

	public BizDepartmentType getType() {
		return type;
	}

	public void setType(BizDepartmentType type) {
		this.type = type;
	}
}
