package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.model.shared.StringIdentifier;
import cn.com.starest.nextoa.project.domain.model.BiddingAgent;
import org.springframework.beans.BeanUtils;

/**
 * @author dz
 */
public class BiddingAgentSummary extends StringIdentifier {

	public static void convert(BiddingAgent fromObj, BiddingAgentSummary result) {
		BeanUtils.copyProperties(fromObj, result);
	}

	public static BiddingAgentSummary from(BiddingAgent fromObj) {
		if (fromObj == null) {
			return null;
		}
		BiddingAgentSummary result = new BiddingAgentSummary();
		convert(fromObj, result);
		return result;
	}

	private String name;

	private String description;

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
}
