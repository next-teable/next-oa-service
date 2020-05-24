package in.clouthink.nextoa.bl.openapi.support;

import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.openapi.dto.MessageDetail;
import in.clouthink.nextoa.bl.openapi.dto.MessageParticipant;
import in.clouthink.nextoa.bl.openapi.dto.MessageQueryParameter;
import in.clouthink.nextoa.bl.openapi.dto.MessageSummary;
import in.clouthink.nextoa.shared.domain.params.PageQueryParameter;
import in.clouthink.nextoa.shared.domain.request.PageQueryRequest;
import org.springframework.data.domain.Page;

/**
 *
 */
public interface MessageRestSupport {

	Page<MessageSummary> listMessagesByTitle(String title, PageQueryRequest queryParameter, User user);

	Page<MessageSummary> listMessagesByPaperCreator(String creatorName, PageQueryRequest queryParameter, User user);

	Page<MessageSummary> listMessagesByReceiver(String receiverName, PageQueryRequest queryParameter, User user);

	Page<MessageSummary> listAllMessages(MessageQueryParameter queryRequest, User user);

	Page<MessageSummary> listPendingMessages(MessageQueryParameter queryRequest, User user);

	Page<MessageSummary> listProcessedMessages(MessageQueryParameter queryRequest, User user);

	Page<MessageSummary> listNotEndMessages(MessageQueryParameter queryRequest, User user);

	Page<MessageSummary> listEndedMessages(MessageQueryParameter queryRequest, User user);

	Page<MessageSummary> listFavoriteMessages(MessageQueryParameter queryRequest, User user);

	MessageDetail getMessageDetail(String id, User user);

	MessageParticipant getMessageParticipant(String id, User user);

	void addMessageToFavorite(String id, User user);

	void removeMessageFromFavorite(String id, User user);

	long getCountOfAllMessages(MessageQueryParameter queryRequest, User user);

	long getCountOfPendingMessages(MessageQueryParameter queryRequest, User user);

	long getCountOfProcessedMessages(MessageQueryParameter queryRequest, User user);

	long getCountOfEndedMessages(MessageQueryParameter queryRequest, User user);

	long getCountOfNotEndMessages(MessageQueryParameter queryRequest, User user);

	long getCountOfFavoriteMessages(PageQueryParameter queryRequest, User user);

}
