package in.clouthink.nextoa.bl.request;


import in.clouthink.nextoa.bl.model.MessageStatus;
import in.clouthink.nextoa.shared.domain.request.DateRangedQueryRequest;

/**
 * 消息查询参数
 */
public interface MessageQueryRequest extends DateRangedQueryRequest {

	enum IncludeOrExcludeStatus {
		INCLUDE, EXCLUDE
	}

	/**
	 * @return 消息类型
	 */
	String getCategory();

	/**
	 * @return 快文标题
	 */
	String getTitle();

	/**
	 * @return 快文发起人
	 */
	String getInitiatorUsername();

	/**
	 * @return 消息状态
	 */
	MessageStatus getMessageStatus();

}
