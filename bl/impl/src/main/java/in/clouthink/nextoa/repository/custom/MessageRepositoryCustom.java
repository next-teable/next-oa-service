package in.clouthink.nextoa.repository.custom;

import in.clouthink.nextoa.model.Message;
import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.model.dtr.MessageQueryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author dz
 */
public interface MessageRepositoryCustom {

	Page<Message> queryPage(User receiver,
							MessageQueryRequest queryRequest,
							MessageQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus);

	Page<Message> queryPageByPaperCreator(String username, User byWho, Pageable pageable);

	Page<Message> queryPageByReceiver(String username, User byWho, Pageable pageable);

	long queryCount(User receiver,
					MessageQueryRequest queryRequest,
					MessageQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus);

	void markAsRead(String id);

}
