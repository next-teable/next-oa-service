package cn.com.starest.nextoa.openapi.dto;

import cn.com.starest.nextoa.model.PaperActionType;
import cn.com.starest.nextoa.model.dtr.StartPaperRequest;

import java.util.List;

/**
 *
 */
public class StartPaperRequestParameter extends AbstractPaperRequestParameter implements StartPaperRequest {

	private List<PaperActionType> disabledActions;

	@Override
	public List<PaperActionType> getDisabledActions() {
		return disabledActions;
	}

	public void setDisabledActions(List<PaperActionType> disabledActions) {
		this.disabledActions = disabledActions;
	}
}
