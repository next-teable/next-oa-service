package in.clouthink.nextoa.bl.openapi.dto;

import in.clouthink.nextoa.bl.model.PaperAction;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 *
 */
@ApiModel
public class PaperTransitionSummary extends PaperActionSummary {

	public static PaperTransitionSummary from(PaperAction action) {
		if (action == null) {
			return null;
		}
		PaperTransitionSummary result = new PaperTransitionSummary();
		convert(action, result);
		result.setTo(ReceiverSummary.from(action.getToReceivers()));
		result.setCc(ReceiverSummary.from(action.getCcReceivers()));
		return result;
	}

	private List<ReceiverSummary> to;

	private List<ReceiverSummary> cc;

	public List<ReceiverSummary> getTo() {
		return to;
	}

	public void setTo(List<ReceiverSummary> to) {
		this.to = to;
	}

	public List<ReceiverSummary> getCc() {
		return cc;
	}

	public void setCc(List<ReceiverSummary> cc) {
		this.cc = cc;
	}
}
