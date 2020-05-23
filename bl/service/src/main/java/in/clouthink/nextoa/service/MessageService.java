package in.clouthink.nextoa.service;

import in.clouthink.nextoa.model.FavoriteMessage;
import in.clouthink.nextoa.model.Message;
import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.model.dtr.MessageQueryRequest;
import in.clouthink.nextoa.model.dtr.PageQueryRequest;
import org.springframework.data.domain.Page;

/**
 */
public interface MessageService {

	Page<Message> listMessagesByPaperCreator(String creatorName, PageQueryRequest queryParameter, User user);

	Page<Message> listMessagesByReceiver(String receiverName, PageQueryRequest queryParameter, User user);

	Message findMessageById(String id);

	/**
	 * 根据id查询消息, 通过user判断是否有权限
	 *
	 * @param id
	 * @param user
	 * @return
	 */
	Message findMessageById(String id, User user);

	Page<Message> listMessages(MessageQueryRequest queryParameter,
                               MessageQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus,
                               User user);

	Page<Message> listPaperMessages(String bizRefId, PageQueryRequest queryParameter);

	Page<Message> listFavoriteMessages(MessageQueryRequest queryParameter, User user);

	long countOfMessages(MessageQueryRequest queryParameter,
						 MessageQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus,
						 User user);

	long countOfFavoriteMessages(PageQueryRequest queryParameter, User user);

	FavoriteMessage addMessageToFavorite(String id, User user);

	void removeMessageFromFavorite(String id, User user);

	boolean isFavorite(Message message, User user);

}
