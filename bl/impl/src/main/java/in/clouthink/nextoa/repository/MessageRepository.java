package in.clouthink.nextoa.repository;

import in.clouthink.nextoa.model.Message;
import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.repository.custom.MessageRepositoryCustom;
import in.clouthink.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author dz
 */
public interface MessageRepository extends AbstractRepository<Message>, MessageRepositoryCustom {

	@Deprecated
	Page<Message> findByTitleLikeAndReceiver(String title, User receiver, Pageable pageable);

	List<Message> findByBizRefId(String bizRefId);

	Message findByBizRefIdAndReceiver(String bizRefId, User user);

	Page<Message> findPageByBizRefId(String bizRefId, Pageable pageable);

}
