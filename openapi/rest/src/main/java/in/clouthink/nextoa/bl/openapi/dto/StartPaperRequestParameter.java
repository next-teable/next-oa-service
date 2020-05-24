package in.clouthink.nextoa.bl.openapi.dto;

import in.clouthink.nextoa.bl.model.PaperActionType;
import in.clouthink.nextoa.bl.request.StartPaperRequest;

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
