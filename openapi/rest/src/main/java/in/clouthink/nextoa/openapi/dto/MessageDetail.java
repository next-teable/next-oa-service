package in.clouthink.nextoa.openapi.dto;

import in.clouthink.nextoa.model.Message;
import in.clouthink.nextoa.model.Paper;
import io.swagger.annotations.ApiModel;

/**
 *
 */
@ApiModel
public class MessageDetail extends MessageSummary {

	public static MessageDetail from(Message message, Paper paper) {
		if (message == null) {
			return null;
		}
		MessageDetail result = new MessageDetail();
		PaperDetail paperDetail = PaperDetail.from(paper);
		convert(message, paper, result);
		result.setBizDetail(paperDetail);
		return result;
	}

	//对应业务明细,例如PaperDetail
	Object bizDetail;

	public Object getBizDetail() {
		return bizDetail;
	}

	public void setBizDetail(Object bizDetail) {
		this.bizDetail = bizDetail;
	}

}