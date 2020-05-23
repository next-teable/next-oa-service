package in.clouthink.nextoa.openapi.dto;

import in.clouthink.nextoa.model.PaperAction;
import io.swagger.annotations.ApiModel;

import java.util.Date;

/**
 *
 */
@ApiModel
public class PaperPrintSummary extends PaperActionSummary {

	public static PaperPrintSummary from(PaperAction action) {
		if (action == null) {
			return null;
		}
		PaperPrintSummary result = new PaperPrintSummary();
		convert(action, result);
		result.setLatestPrintAt(action.getModifiedAt());
		return result;
	}

	private Date latestPrintAt;

	public Date getLatestPrintAt() {
		return latestPrintAt;
	}

	public void setLatestPrintAt(Date latestPrintAt) {
		this.latestPrintAt = latestPrintAt;
	}
}
