package cn.com.starest.nextoa.openapi.dto;

import cn.com.starest.nextoa.model.Receiver;
import cn.com.starest.nextoa.model.dtr.AbstractPaperRequest;

import java.util.List;

/**
 *
 */
public class AbstractPaperRequestParameter implements AbstractPaperRequest {

	//快文正文
	private String paperContent;

	//主送
	private List<Receiver> toReceivers;

	//抄送
	private List<Receiver> ccReceivers;

	@Override
	public String getPaperContent() {
		return paperContent;
	}

	public void setPaperContent(String paperContent) {
		this.paperContent = paperContent;
	}

	@Override
	public List<Receiver> getToReceivers() {
		return toReceivers;
	}

	public void setToReceivers(List<Receiver> toReceivers) {
		this.toReceivers = toReceivers;
	}

	@Override
	public List<Receiver> getCcReceivers() {
		return ccReceivers;
	}

	public void setCcReceivers(List<Receiver> ccReceivers) {
		this.ccReceivers = ccReceivers;
	}

}
