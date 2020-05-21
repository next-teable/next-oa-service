package cn.com.starest.nextoa.openapi.dto;

import cn.com.starest.nextoa.model.PaperActionType;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 *
 */
@ApiModel
public class StartPaperParameter extends AbstractPaperParameter {

	//PRINT,EDIT,COPY,FORWARD
	private List<PaperActionType> disabledActions;

	public List<PaperActionType> getDisabledActions() {
		return disabledActions;
	}

	public void setDisabledActions(List<PaperActionType> disabledActions) {
		this.disabledActions = disabledActions;
	}
}
