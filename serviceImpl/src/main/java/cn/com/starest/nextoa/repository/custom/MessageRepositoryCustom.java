package cn.com.starest.nextoa.repository.custom;

import cn.com.starest.nextoa.model.Message;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.MessageQueryRequest;
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
