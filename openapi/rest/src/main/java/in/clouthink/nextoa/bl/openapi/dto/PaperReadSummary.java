package in.clouthink.nextoa.bl.openapi.dto;

import in.clouthink.nextoa.bl.model.PaperAction;
import io.swagger.annotations.ApiModel;

import java.util.Date;

/**
 *
 */
@ApiModel
public class PaperReadSummary extends PaperActionSummary {

	public static PaperReadSummary from(PaperAction action) {
		if (action == null) {
			return null;
		}
		PaperReadSummary result = new PaperReadSummary();
		convert(action, result);
		result.setLatestReadAt(action.getModifiedAt());
		return result;
	}

	private Date latestReadAt;

	public Date getLatestReadAt() {
		return latestReadAt;
	}

	public void setLatestReadAt(Date latestReadAt) {
		this.latestReadAt = latestReadAt;
	}
}
